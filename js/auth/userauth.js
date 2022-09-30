function userLogin(event){
    event.preventDefault();
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    if(
        !email ||
        !password
    ){
        alert('All the fields are required...');
        location.reload();
        return;
    }
    fetch("user/login",{
        method:"POST",
        headers:{
            "Content-type":"application/json"
        },
        body:JSON.stringify({email,password})
    }).then(resp=>{
        if(resp.status==200){
            location.href='/bank1/auth/evalsecret.jsp';
            return;
        }else{
            resp.text().then(resp=>{
                alert(resp);
                location.reload();
                return;
            })
        }
    }).catch(err=>{
        alert(err);
        location.reload();
    });
}

function handleCredentialResponse(cred){
    console.log(cred.clientId);
    console.log(cred.credential);
    fetch('/bank1/login/google',{
        method:"POST",
        body:JSON.stringify({
        code:cred.credential,
        role:"user"
    })
    }).then(resp=>{
        if(resp.status==200){
            location.href="/bank1/menu/usermenu.jsp";
            return;
        }else{
            resp.text().then(text=>{
                alert(text);
            });
        }
    });
}