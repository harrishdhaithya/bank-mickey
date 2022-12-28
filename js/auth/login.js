function adminsignin(event) {
    event.preventDefault();
    const email = document.getElementById("email").value;
    const password = document.getElementById("password").value;
    if(!email||!password){
        alert('All the fields are required');
        location.reload();
    }
    fetch("/bank1/auth/login",{
        method:"POST",
        headers:{
            "Content-type":"application/json"
        },
        body:JSON.stringify({
            email:email,
            password:password
        })
    }).then(resp=>{
        if(resp.status==200){
            console.log("Redirecting...");
            location.href='/bank1/auth/evalsecret.jsp';
            return;
        }else{
            resp.text().then(text=>{
                console.log(text);
                alert("Something went wrong...");
            });
        }
    }).catch(err=>{
        alert(err);
    });
}


// function handleCredentialResponse(cred){
//     console.log(cred.clientId);
//     console.log(cred.credential);
//     fetch('/bank1/login/google',{
//         method:"POST",
//         body:JSON.stringify({
//         code:cred.credential,
//         role:"admin"
//     })
//     }).then(resp=>{
//         if(resp.status==200){
//             location.href="/bank1/menu/adminmenu.jsp";
//             return;
//         }else{
//             resp.text().then(text=>{
//                 alert(text);
//             });
//         }
//     })
// }
