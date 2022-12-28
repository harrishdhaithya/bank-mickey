function evalCode(event){
    event.preventDefault();
    const code = document.getElementById("code").value;
    if(!code){
        alert('Enter a valid code...');
        return;
    }
    const param = new URLSearchParams({code:code.trim()});
    fetch(`user/secret?${param.toString()}`).then(resp=>{
        if(resp.redirected){
            location.href=resp.url;
        }else{
            resp.text().then(text=>alert(text));
            location.href="/bank1"
        }
    });
}