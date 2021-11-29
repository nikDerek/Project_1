

function getAllRequests(){
	
	var user = localStorage.getItem("userName");
	console.log(userName);
	
	let user_hold = document.getElementById("user_name");
	
	user_hold.innerText = userName;
	
	document.getElementById("user_input").value = userName;
	
    let url = 'http://localhost:8080/request/';
    
    url = url + user_hold.innerText;
    

    // Using the ready states of an XMLHttpRequest can guide your AJAX workflow.
    let xhr = new XMLHttpRequest(); //readyState is 0
    console.log(xhr);


    
    xhr.onreadystatechange = function(){
        
        if(xhr.readyState === 4 && xhr.status === 200){
            
            let requests = JSON.parse(xhr.response);

            console.log(requests);
            
			
            for(let request of requests){
                

            let request_container = document.getElementById('request_container');

            

            let new_div = document.createElement('div');
            new_div.className = "request_div";

      		let new_h32 = document.createElement('h3');
			new_h32.innerText = request.requestId;
			
            let new_h3 = document.createElement('h3');
            new_h3.innerText = "Employee: " + request.userName;

            let new_para = document.createElement('p');
			new_para.innerText = "Amount: $" + request.requestAmount;

            let new_para2 = document.createElement('p');
            new_para2.innerText = "Description: " + request.request4;
            
            let new_para3 = document.createElement('p');
            new_para3.innerText = "Status: " + request.requestStatus;

            new_div.appendChild(new_h32);
            new_div.appendChild(new_h3);
            new_div.appendChild(new_para);
            new_div.appendChild(new_para2);
            new_div.appendChild(new_para3);

            request_container.appendChild(new_div);
            }
            console.log(user_hold.innerText);
        }
    }
    
    
    xhr.open('GET', url); //readyState is 1

   
    xhr.send(); //readyState is 2
    
}

function logout() {

    localStorage.setItem("userName", null);
    
    window.location.replace("http://localhost:8080/LogIn.html");

}


window.onload = function(){
    this.getAllRequests();
}
