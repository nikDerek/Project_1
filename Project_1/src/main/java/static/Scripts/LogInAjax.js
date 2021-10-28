function login(){
	
	var user = JSON.parse(xhr.response);
    
    localStorage.setItem("user", user);
    
}