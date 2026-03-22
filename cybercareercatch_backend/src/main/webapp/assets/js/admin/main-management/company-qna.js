document.addEventListener("DOMContentLoaded", function () {
    const noticeBox = document.querySelector("#notice-box");
    const noticeForm = document.querySelector("#qnaNoticeForm");
    const deleteForm = document.querySelector("#qnaDeleteForm");

    if (noticeForm) {
        noticeForm.addEventListener("submit", function (event) {
            if (!noticeBox || !noticeBox.value.trim()) {
                alert("공지 내용을 입력하세요.");
                event.preventDefault();
            }
        });
    }

    if (deleteForm) {
        deleteForm.addEventListener("submit", function (event) {
            const checkedList = deleteForm.querySelectorAll("input[name='postNumber']:checked");

            if (checkedList.length === 0) {
                alert("삭제할 게시글을 선택하세요.");
                event.preventDefault();
                return;
            }

            const isConfirmed = confirm("선택한 게시글을 삭제하시겠습니까?");
            if (!isConfirmed) {
                event.preventDefault();
            }
        });
    }
});