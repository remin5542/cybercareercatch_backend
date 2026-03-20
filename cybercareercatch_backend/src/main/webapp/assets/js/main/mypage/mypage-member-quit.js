// 회원탈퇴 폼
const quitForm = document.getElementById("member-quit-form");

// 비밀번호 입력칸
const passwordInput = document.getElementById("member-quit-password");

// 에러 메시지 영역
const pwError = document.getElementById("pwError");

// 확인 버튼
const confirmBtn = document.getElementById("confirm");

// 메시지 출력 함수
function showMessage(message, color = "red") {
	if (!pwError) return;
	pwError.textContent = message;
	pwError.style.color = color;
}

// 비밀번호 입력 시 메시지 초기화
if (passwordInput) {
	passwordInput.addEventListener("input", function () {
		showMessage("");
	});
}

// 폼 제출 시 검사
if (quitForm) {
	quitForm.addEventListener("submit", function (e) {
		const userPw = passwordInput.value.trim();

		// 공백 체크
		if (userPw === "") {
			e.preventDefault();
			showMessage("비밀번호를 입력해주세요.");
			passwordInput.focus();
			return;
		}

		// 최종 확인창
		const isDelete = confirm("정말 회원탈퇴 하시겠습니까? 탈퇴 후에는 되돌릴 수 없습니다.");

		if (!isDelete) {
			e.preventDefault();
		}
	});
}