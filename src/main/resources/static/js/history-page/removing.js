async function delete_target_button(event) {

    event.preventDefault();

    const button = event.target;
    const id = button.id;

    try {

        await fetch('/translate/deleteNote', {

            method: 'delete',
            headers: {
                'content-type': 'application/json'
            },
            body: JSON.stringify({
                id: id,
                wrongString: "something for validation passing"
            })
        });

        await reset_after_removing();

    } catch (e) {
        console.log(e);
    }

}

async function delete_all_button(event) {

    event.preventDefault();

    try {

        await fetch('/translate/delete', {
            method: 'delete'
        });

        await reset_after_removing();

    } catch (e) {
        console.log(e);
    }

}