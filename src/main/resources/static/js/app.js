const sign_in_btn = document.querySelector("#sign-in-btn");
const sign_up_btn = document.querySelector("#sign-up-btn");
const container = document.querySelector(".container");

sign_up_btn.addEventListener("click", () => {
  container.classList.add("sign-up-mode");
});

sign_in_btn.addEventListener("click", () => {
  container.classList.remove("sign-up-mode");
});
function fnView() {
  const passwordField = document.getElementById("pass");
  const eyeIcon = document.getElementById("btnView");

  if (passwordField.value.length > 0 && passwordField.type === "password") {
    passwordField.type = "text";
    eyeIcon.innerHTML = '<i class="fa-regular fa-eye"></i>';
    eyeIcon.title = 'sembunyikan password';
  } else {
    passwordField.type = "password";
    eyeIcon.innerHTML = '<i class="fa-regular fa-eye-slash"></i>';
    eyeIcon.title = 'lihat password';
  }
}




