// 이동 경로
const mypageUrl = "/cybercareercatch_backend/member/mypage.mpfc";


function showMessage(target, message, color = "red") {
	if (!target) return;
	target.textContent = message;
	target.style.color = color;
}

function validatePhone(phone) {
	// 010 + 숫자 8개
	return /^010\d{8}$/.test(phone);
}

function validatePassword(password) {
	// 영문 + 숫자 + 특수기호 포함, 8~20자
	return /^(?=.*[A-Za-z])(?=.*\d)(?=.*[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]).{8,20}$/.test(password);
}

//전화번호 수정
const phoneForm = document.getElementById("member-phone-form");
const phoneInput = document.getElementById("member-phonenumber");
const phoneMsg = document.getElementById("member-phonenumber-message");

const authInput = document.getElementById("member-verificationcode");
const authMsg = document.getElementById("member-verificationcode-message");

const sendBtn = document.getElementById("member-phonenumber-submit-btn");
const authCheckBtn = document.getElementById("member-verificationcode-btn");
const phoneCancelBtn = document.getElementById("mypage-infoeditcancel-btn");

let authCode = "";
let phoneVerified = false;

// 인증번호 전송
if (sendBtn) {
	sendBtn.addEventListener("click", function () {
		const phoneValue = phoneInput.value.trim();

		if (phoneValue === "") {
			showMessage(phoneMsg, "전화번호를 입력해주세요.");
			phoneVerified = false;
			return;
		}

		if (!validatePhone(phoneValue)) {
			showMessage(phoneMsg, "전화번호는 010으로 시작하는 11자리 숫자만 입력 가능합니다.");
			phoneVerified = false;
			return;
		}

		authCode = String(Math.floor(100000 + Math.random() * 900000));
		phoneVerified = false;

		showMessage(phoneMsg, "인증번호가 전송되었습니다.", "green");
		showMessage(authMsg, "");

		alert("테스트용 인증번호: " + authCode);
	});
}

// 인증번호 확인
if (authCheckBtn) {
	authCheckBtn.addEventListener("click", function () {
		const inputCode = authInput.value.trim();

		if (authCode === "") {
			showMessage(authMsg, "먼저 인증번호를 전송해주세요.");
			phoneVerified = false;
			return;
		}

		if (inputCode === "") {
			showMessage(authMsg, "인증번호를 입력해주세요.");
			phoneVerified = false;
			return;
		}

		if (inputCode === authCode) {
			showMessage(authMsg, "인증이 완료되었습니다.", "green");
			phoneVerified = true;
		} else {
			showMessage(authMsg, "인증번호가 일치하지 않습니다.");
			phoneVerified = false;
		}
	});
}

// 전화번호 바뀌면 인증 초기화
if (phoneInput) {
	phoneInput.addEventListener("input", function () {
		authCode = "";
		phoneVerified = false;
		authInput.value = "";
		showMessage(phoneMsg, "");
		showMessage(authMsg, "");
	});
}

// 전화번호 수정 submit
if (phoneForm) {
	phoneForm.addEventListener("submit", function (e) {
		const phoneValue = phoneInput.value.trim();

		if (phoneValue === "") {
			e.preventDefault();
			showMessage(phoneMsg, "전화번호를 입력해주세요.");
			phoneInput.focus();
			return;
		}

		if (!validatePhone(phoneValue)) {
			e.preventDefault();
			showMessage(phoneMsg, "전화번호는 010으로 시작하는 11자리 숫자만 입력 가능합니다.");
			phoneInput.focus();
			return;
		}

		if (!phoneVerified) {
			e.preventDefault();
			showMessage(authMsg, "전화번호 인증을 완료해주세요.");
			authInput.focus();
		}
	});
}

// 전화번호 수정 취소
if (phoneCancelBtn) {
	phoneCancelBtn.addEventListener("click", function () {
		if (confirm("수정 중인 내용이 저장되지 않을 수 있습니다. 취소하시겠습니까?")) {
			location.href = mypageUrl;
		}
	});
}

//비밀번호 수정
const pwForm = document.getElementById("member-password-form");

const currentPwInput = document.getElementById("member-current-pw");
const currentPwMsg = document.getElementById("member-current-pw-message");

const newPwInput = document.getElementById("member-change-pw");
const newPwMsg = document.getElementById("member-change-pw-message");

const newPwConfirmInput = document.getElementById("member-check-pw");
const newPwConfirmMsg = document.getElementById("member-check-pw-message");

const currentPwBtn = document.getElementById("currentpw-check-btn");
const newPwCheckBtn = document.getElementById("changepw-check-btn");
const pwCancelBtn = document.getElementById("mypage-pweditcancel-btn");


// 새 비밀번호 확인 버튼
if (newPwCheckBtn) {
	newPwCheckBtn.addEventListener("click", function () {
		const currentPw = currentPwInput.value.trim();
		const newPw = newPwInput.value.trim();
		const newPwConfirm = newPwConfirmInput.value.trim();

		showMessage(newPwMsg, "");
		showMessage(newPwConfirmMsg, "");

		if (newPw === "") {
			showMessage(newPwMsg, "변경할 비밀번호를 입력해주세요.");
			newPwInput.focus();
			return;
		}

		if (!validatePassword(newPw)) {
			showMessage(newPwMsg, "비밀번호는 영문, 숫자, 특수기호를 포함한 8~20자여야 합니다.");
			newPwInput.focus();
			return;
		}


		if (newPwConfirm === "") {
			showMessage(newPwConfirmMsg, "변경할 비밀번호 확인을 입력해주세요.");
			newPwConfirmInput.focus();
			return;
		}

		if (newPw !== newPwConfirm) {
			showMessage(newPwConfirmMsg, "변경할 비밀번호가 일치하지 않습니다.");
			newPwConfirmInput.focus();
			return;
		}

		showMessage(newPwMsg, "사용 가능한 비밀번호 형식입니다.", "green");
		showMessage(newPwConfirmMsg, "비밀번호가 일치합니다.", "green");
	});
}

// 비밀번호 입력값 바뀌면 메시지 초기화
if (newPwInput) {
	newPwInput.addEventListener("input", function () {
		showMessage(newPwMsg, "");
		showMessage(newPwConfirmMsg, "");
	});
}

if (newPwConfirmInput) {
	newPwConfirmInput.addEventListener("input", function () {
		showMessage(newPwConfirmMsg, "");
	});
}

// 비밀번호 수정 submit
if (pwForm) {
	pwForm.addEventListener("submit", function (e) {
		const currentPw = currentPwInput.value.trim();
		const newPw = newPwInput.value.trim();
		const newPwConfirm = newPwConfirmInput.value.trim();

		if (currentPw === "") {
			e.preventDefault();
			showMessage(currentPwMsg, "현재 비밀번호를 입력해주세요.");
			currentPwInput.focus();
			return;
		}

		if (newPw === "") {
			e.preventDefault();
			showMessage(newPwMsg, "변경할 비밀번호를 입력해주세요.");
			newPwInput.focus();
			return;
		}

		if (!validatePassword(newPw)) {
			e.preventDefault();
			showMessage(newPwMsg, "비밀번호는 영문, 숫자, 특수기호를 포함한 8~20자여야 합니다.");
			newPwInput.focus();
			return;
		}


		if (newPwConfirm === "") {
			e.preventDefault();
			showMessage(newPwConfirmMsg, "변경할 비밀번호 확인을 입력해주세요.");
			newPwConfirmInput.focus();
			return;
		}

		if (newPw !== newPwConfirm) {
			e.preventDefault();
			showMessage(newPwConfirmMsg, "변경할 비밀번호가 일치하지 않습니다.");
			newPwConfirmInput.focus();
		}
	});
}

// 비밀번호 수정 취소
if (pwCancelBtn) {
	pwCancelBtn.addEventListener("click", function () {
		if (confirm("비밀번호 변경 내용을 취소하시겠습니까?")) {
			location.href = mypageUrl;
		}
	});
}