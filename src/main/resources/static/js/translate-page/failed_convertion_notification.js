async function failedNotification(errorText) {

    await hideSuccessModal();

    const div = document.getElementById("failed-convertion-notification");

    const span = document.getElementById("failed-convertion-notification-text");

    span.innerText = errorText;

    div.style.display = "block";

}

async function hideFailedModal() {

    const div = document.getElementById("failed-convertion-notification");

    div.style.display = "none";

}