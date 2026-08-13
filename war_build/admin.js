document.querySelector('form').addEventListener('submit',function(e){
    const user = document.getElementsByName('username')[0].value.trim();
    const pass = document.getElementsByName('password')[0].value.trim();
    if (user ==="" || pass===""){
        e.preventDefault();
        alert("Please enter both username and password!");
    }
});