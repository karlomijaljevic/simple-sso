/**
 * Endpoint for user sign in.
 */
const AUTH_SIGN_IN_ENDPOINT = "/auth/sign-in"

/**
 * Activated by the 'auth-form' on submit call. Calls the '/auth/sign-in'
 * endpoint to verify if the user is registered.
 */
async function signIn(event) {
	event.preventDefault();
	
	const reqUrl = window.origin + AUTH_SIGN_IN_ENDPOINT;
	
	const request = {
		email: document.getElementById("form-auth-email").value,
		password: document.getElementById("form-auth-password").value
	}
	
	const response = await fetch(reqUrl, {
		method: "POST",
		body: JSON.stringify(request),
		headers: {
			"Content-Type": "application/json"
		}
	}).then(response => response.json());
	
	if(response.success) {
		// TODO: Handle user redirecton.
	} else {
		// TODO: Create an alert element.
	}
	
	displayLoader(0);
}

/**
 * Shows or hides the 'auth-loader' loader on the page depending on the
 * provided parameter which can either be 1 or 0. If 1 is provided then the
 * loader is displayed, if 0 is provided then the loader is hidden.
 */
function displayLoader(display) {
	const loader = document.getElementById("auth-loader");
	const button = document.getElementById("submit-button");
	
	if(display === 1) {
		button.disabled = true;
		/**
		 * When displaying the loader we want it displayed only when the user
		 * is waiting at least 500ms.
		 */
		setTimeout(() => {
			loader.style.display = "inherit";
		}, 200);
	} else if(display === 0) {
		button.disabled = false;
		loader.style.display = "none";
	} else {
		console.error("Unsupported number provided!");
	}
}