document.addEventListener("DOMContentLoaded", function () {
	// 공통 메시지 출력 함수
	// target : 메시지를 보여줄 요소
	// message : 출력할 문구
	// color : 글자색, 기본값은 red
	function showMessage(target, message, color = "red") {
		// target 요소가 없으면 종료
		if (!target) return;

		// 메시지 텍스트 넣기
		target.textContent = message;

		// 메시지 색상 적용
		target.style.color = color;
	}

	// 전화번호 형식 검사 함수
	// 010으로 시작하는 11자리 숫자인지 확인
	function validatePhone(phone) {
		return /^010\d{8}$/.test(phone);
	}

	// 비밀번호 형식 검사 함수
	// 영문, 숫자, 특수기호를 포함하고 8~20자인지 확인
	function validatePassword(password) {
		return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,20}$/.test(password);
	}

	// ====================
	// 전화번호 수정
	// ====================

	// 전화번호 수정 form 요소 가져오기
	const phoneForm = document.getElementById("member-phone-form");

	// 전화번호 입력칸 요소 가져오기
	const phoneInput = document.getElementById("member-phonenumber");

	// 전화번호 관련 메시지 출력 요소 가져오기
	const phoneMsg = document.getElementById("member-phonenumber-message");

	// 인증번호 입력칸 요소 가져오기
	const authInput = document.getElementById("member-verificationcode");

	// 인증번호 관련 메시지 출력 요소 가져오기
	const authMsg = document.getElementById("member-verificationcode-message");

	// 인증번호 발송 버튼 요소 가져오기
	const sendBtn = document.getElementById("member-phonenumber-submit-btn");

	// 인증번호 확인 버튼 요소 가져오기
	const authCheckBtn = document.getElementById("member-verificationcode-btn");

	// 전화번호 수정 취소 버튼 요소 가져오기
	const phoneCancelBtn = document.getElementById("mypage-infoeditcancel-btn");

	// 전화번호 인증 완료 여부를 저장하는 변수
	let phoneVerified = false;

	// 인증번호 발송 버튼이 있으면 클릭 이벤트 등록
	if (sendBtn) {
		sendBtn.addEventListener("click", function (e) {
			// 기본 submit 동작 막기
			e.preventDefault();

			// 입력한 전화번호 값 가져오기
			const phoneValue = phoneInput.value.trim();

			// 전화번호가 비어 있으면 메시지 출력 후 종료
			if (phoneValue === "") {
				showMessage(phoneMsg, "전화번호를 입력해주세요.");
				phoneVerified = false;
				return;
			}

			// 전화번호 형식이 맞지 않으면 메시지 출력 후 종료
			if (!validatePhone(phoneValue)) {
				showMessage(phoneMsg, "전화번호는 010으로 시작하는 11자리 숫자만 입력 가능합니다.");
				phoneVerified = false;
				return;
			}

			// 서버에 인증번호 발송 요청 보내기
			fetch(`${contextPath}/member/sendSMS.mefc`, {
				method: "POST",
				headers: {
					"Content-Type": "application/x-www-form-urlencoded",
					"X-Requested-With": "XMLHttpRequest"
				},
				body: new URLSearchParams({ phoneNumber: phoneValue })
			})
			.then(response => {
				// 응답이 실패면 에러 발생
				if (!response.ok) throw new Error("SMS 발송 실패");

				// 텍스트 응답 반환
				return response.text();
			})
			.then(message => {
				// 응답 메시지 콘솔 출력
				console.log(message);

				// 인증은 아직 안 끝났으므로 false 유지
				phoneVerified = false;

				// 인증번호 입력칸 활성화
				authInput.disabled = false;

				// 기존 입력값 비우기
				authInput.value = "";

				// 인증번호 입력칸으로 포커스 이동
				authInput.focus();

				// 전화번호 메시지 영역에 성공 메시지 출력
				showMessage(phoneMsg, "인증번호가 전송되었습니다.", "green");

				// 인증번호 메시지는 초기화
				showMessage(authMsg, "");
			})
			.catch(error => {
				// 에러 콘솔 출력
				console.error("SMS 발송 오류:", error);

				// 인증번호 발송 실패 메시지 출력
				showMessage(authMsg, "인증번호 발송 중 오류가 발생했습니다.");

				// 인증 완료 상태 false 처리
				phoneVerified = false;
			});
		});
	}

	// 인증번호 확인 버튼이 있으면 클릭 이벤트 등록
	if (authCheckBtn) {
		authCheckBtn.addEventListener("click", function (e) {
			// 기본 submit 동작 막기
			e.preventDefault();

			// 입력한 인증번호 값 가져오기
			const inputCode = authInput.value.trim();

			// 인증번호가 비어 있으면 메시지 출력 후 종료
			if (inputCode === "") {
				showMessage(authMsg, "인증번호를 입력해주세요.");
				phoneVerified = false;
				return;
			}

			// 서버에 인증번호 확인 요청 보내기
			fetch(`${contextPath}/member/verifyCode.mefc`, {
				method: "POST",
				headers: {
					"Content-Type": "application/json; charset=utf-8",
					"Accept": "application/json"
				},
				body: JSON.stringify({ code: inputCode })
			})
			.then(response => {
				// 응답이 실패면 에러 발생
				if (!response.ok) throw new Error("인증 확인 실패");

				// JSON 응답 반환
				return response.json();
			})
			.then(data => {
				// 인증 성공 시
				if (data.success) {
					showMessage(authMsg, "인증이 완료되었습니다.", "green");
					phoneVerified = true;
				} else {
					// 인증 실패 시
					showMessage(authMsg, "인증번호가 일치하지 않습니다.");
					phoneVerified = false;
				}
			})
			.catch(error => {
				// 에러 콘솔 출력
				console.error("인증 확인 오류:", error);

				// 인증 처리 오류 메시지 출력
				showMessage(authMsg, "인증 처리 중 오류가 발생했습니다.");

				// 인증 완료 상태 false 처리
				phoneVerified = false;
			});
		});
	}

	// 전화번호 입력값이 바뀌면 인증 상태 초기화
	if (phoneInput) {
		phoneInput.addEventListener("input", function () {
			// 전화번호가 바뀌었으므로 다시 인증해야 함
			phoneVerified = false;

			// 인증번호 입력칸이 있으면
			if (authInput) {
				// 입력값 비우기
				authInput.value = "";

				// 다시 비활성화
				authInput.disabled = true;
			}

			// 메시지 초기화
			showMessage(phoneMsg, "");
			showMessage(authMsg, "");
		});
	}

	// 전화번호 수정 form이 있으면 submit 이벤트 등록
	if (phoneForm) {
		phoneForm.addEventListener("submit", function (e) {
			// 현재 입력한 전화번호 값 가져오기
			const phoneValue = phoneInput.value.trim();

			// 전화번호가 비어 있으면 제출 막기
			if (phoneValue === "") {
				e.preventDefault();
				showMessage(phoneMsg, "전화번호를 입력해주세요.");
				phoneInput.focus();
				return;
			}

			// 전화번호 형식이 맞지 않으면 제출 막기
			if (!validatePhone(phoneValue)) {
				e.preventDefault();
				showMessage(phoneMsg, "전화번호는 010으로 시작하는 11자리 숫자만 입력 가능합니다.");
				phoneInput.focus();
				return;
			}

			// 인증이 완료되지 않았으면 제출 막기
			if (!phoneVerified) {
				e.preventDefault();
				showMessage(authMsg, "전화번호 인증을 완료해주세요.");
				authInput.focus();
				return;
			}
		});
	}

	// 전화번호 수정 취소 버튼이 있으면 클릭 이벤트 등록
	if (phoneCancelBtn) {
		phoneCancelBtn.addEventListener("click", function () {
			// 이동할 주소 가져오기
			const moveUrl = phoneCancelBtn.dataset.moveUrl;

			// 취소 여부 확인창 띄우기
			const isOk = confirm("수정 중인 내용이 저장되지 않을 수 있습니다.\n정말 취소하시겠습니까?");

			// 확인을 누르고 이동 주소가 있으면 페이지 이동
			if (isOk && moveUrl) {
				location.href = moveUrl;
			}
		});
	}

	// ====================
	// 비밀번호 수정
	// ====================

	// 비밀번호 수정 form 요소 가져오기
	const pwForm = document.getElementById("member-password-form");

	// 현재 비밀번호 입력칸 요소 가져오기
	const currentPwInput = document.getElementById("member-current-pw");

	// 현재 비밀번호 메시지 출력 요소 가져오기
	const currentPwMsg = document.getElementById("member-current-pw-message");

	// 새 비밀번호 입력칸 요소 가져오기
	const newPwInput = document.getElementById("member-change-pw");

	// 새 비밀번호 메시지 출력 요소 가져오기
	const newPwMsg = document.getElementById("member-change-pw-message");

	// 새 비밀번호 확인 입력칸 요소 가져오기
	const newPwConfirmInput = document.getElementById("member-check-pw");

	// 새 비밀번호 확인 메시지 출력 요소 가져오기
	const newPwConfirmMsg = document.getElementById("member-check-pw-message");

	// 비밀번호 수정 취소 버튼 요소 가져오기
	const pwCancelBtn = document.getElementById("mypage-pweditcancel-btn");

	// 새 비밀번호 입력 시 메시지 초기화
	if (newPwInput) {
		newPwInput.addEventListener("input", function () {
			showMessage(newPwMsg, "");
			showMessage(newPwConfirmMsg, "");
		});
	}

	// 새 비밀번호 확인 입력 시 메시지 초기화
	if (newPwConfirmInput) {
		newPwConfirmInput.addEventListener("input", function () {
			showMessage(newPwConfirmMsg, "");
		});
	}

	// 비밀번호 수정 form이 있으면 submit 이벤트 등록
	if (pwForm) {
		pwForm.addEventListener("submit", function (e) {
			// 각 입력값 가져오기
			const currentPw = currentPwInput.value.trim();
			const newPw = newPwInput.value.trim();
			const newPwConfirm = newPwConfirmInput.value.trim();

			// 기존 메시지 초기화
			showMessage(currentPwMsg, "");
			showMessage(newPwMsg, "");
			showMessage(newPwConfirmMsg, "");

			// 현재 비밀번호가 비어 있으면 제출 막기
			if (currentPw === "") {
				e.preventDefault();
				showMessage(currentPwMsg, "현재 비밀번호를 입력해주세요.");
				currentPwInput.focus();
				return;
			}

			// 새 비밀번호가 비어 있으면 제출 막기
			if (newPw === "") {
				e.preventDefault();
				showMessage(newPwMsg, "변경할 비밀번호를 입력해주세요.");
				newPwInput.focus();
				return;
			}

			// 새 비밀번호 형식이 맞지 않으면 제출 막기
			if (!validatePassword(newPw)) {
				e.preventDefault();
				showMessage(newPwMsg, "비밀번호는 영문, 숫자, 특수기호를 포함한 8~20자여야 합니다.");
				newPwInput.focus();
				return;
			}

			// 새 비밀번호 확인이 비어 있으면 제출 막기
			if (newPwConfirm === "") {
				e.preventDefault();
				showMessage(newPwConfirmMsg, "변경할 비밀번호 확인을 입력해주세요.");
				newPwConfirmInput.focus();
				return;
			}

			// 새 비밀번호와 확인값이 다르면 제출 막기
			if (newPw !== newPwConfirm) {
				e.preventDefault();
				showMessage(newPwConfirmMsg, "변경할 비밀번호가 일치하지 않습니다.");
				newPwConfirmInput.focus();
				return;
			}
		});
	}

	// 비밀번호 수정 취소 버튼이 있으면 클릭 이벤트 등록
	if (pwCancelBtn) {
		pwCancelBtn.addEventListener("click", function () {
			// 이동할 주소 가져오기
			const moveUrl = pwCancelBtn.dataset.moveUrl;

			// 취소 여부 확인창 띄우기
			const isOk = confirm("비밀번호 변경 내용을 취소하시겠습니까?");

			// 확인을 누르고 이동 주소가 있으면 페이지 이동
			if (isOk && moveUrl) {
				location.href = moveUrl;
			}
		});
	}
});