async function convert(event) {

    event.preventDefault();

    const convertion_textarea = document.getElementById("wrong-string-textarea");

    const value_convertion_textarea = convertion_textarea.value;

    try {

        const response = await fetch('/translate/convert', {

                method: 'POST',
                headers: {
                    'content-type': 'application/json'
                },
                body: JSON.stringify({
                    wrongString: value_convertion_textarea
                })
            }
        );

        convertion_textarea.value = "";

        const data = await handleResponse(response);

        await successNotification(data);

        await draw_recent_history_table();

    } catch (error) {

        console.error(error);

    }
}




