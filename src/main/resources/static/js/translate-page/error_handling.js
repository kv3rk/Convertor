async function handleResponse(response) {

    if (response.ok) {

        return await response.json();

    }

    let errorText = "Unknown error";

    try {

        const errorData = await response.json();

        if (errorData.errors && Array.isArray(errorData.errors)) {

            errorText = errorData.errors.join("\n");

        } else if (errorData.message) {

            errorText = errorData.message;

        }

    } catch (e) {

        errorText = "Server returned invalid error response"

    }

    await failedNotification(errorText);

    throw new Error(errorText);

}