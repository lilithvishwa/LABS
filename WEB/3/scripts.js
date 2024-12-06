// Basic JavaScript for form validation

function validateRegistration() {
  const username = document.getElementById("username").value;
  const email = document.getElementById("email").value;
  const password = document.getElementById("password").value;
  const confirmPassword = document.getElementById("confirmPassword").value;

  if (username.trim() === "") {
    alert("Username is required.");
    return false;
  }

  if (email.trim() === "" || !email.includes("@")) {
    alert("Please enter a valid email address.");
    return false;
  }

  if (password.trim() === "") {
    alert("Password is required.");
    return false;
  }

  if (password !== confirmPassword) {
    alert("Passwords do not match.");
    return false;
  }

  alert("Registration successful!");
  return true;
}
