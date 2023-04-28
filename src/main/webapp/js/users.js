function renderUsers(users) {
  const holder = document.querySelector(".surveys");

  const oneUser = document.createElement("div");
  oneUser.setAttribute("class", "survey");

  const userName = document.createElement("p");
  const userEmail = document.createElement("p");
  const actions = document.createElement("div");
  actions.setAttribute("class", "actions");

  const deleteBtn = document.createElement("a");
  deleteBtn.innerHTML = "Delete";
  deleteBtn.setAttribute("class", "delete");

  actions.appendChild(deleteBtn);

  userName.innerHTML = users.user_name;
  userEmail.innerHTML = users.email;

  oneUser.appendChild(userName);
  oneUser.appendChild(userEmail);
  oneUser.appendChild(actions);

  holder.appendChild(oneUser);

  deleteBtn.addEventListener("click", (e) => {
    e.preventDefault();
    deleteBtn.style.fontSize = "15px";
    fetch(
      `http://localhost:8080/askpublic-1.0/users/delete?email=${users.email}`
    )
      .then((res) => res.json())
      .then((data) => {
        alert(data.msg);
        window.location.href = "./users.html";
      })
      .catch((err) => {
        alert(err.message);
      });
  });
}

fetch("http://localhost:8080/askpublic-1.0/users")
  .then((res) => res.json())
  .then((data) => {
    data.payload.forEach((user) => {
      renderUsers(user);
    });
  });
