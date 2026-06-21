async function successNotification(dto) {

    await hideFailedModal();

    const rightString = dto.rightString;

    await createSuccessModal(rightString);

}

async function createSuccessModal(rightString) {

    const div = document.getElementById("success-convertion-notification");

    const span = document.getElementById("success-convertion-notification-text");

    span.innerText = rightString;

    div.style.display = "block";
}

async function hideSuccessModal() {

    const div = document.getElementById("success-convertion-notification");

    div.style.display = "none";

}