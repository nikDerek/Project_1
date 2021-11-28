

function getAllRequests(){
	
	var user = localStorage.getItem("userName");
	console.log(userName);
	
	let user_hold = document.getElementById("user_name");
	
	user_hold.innerText = userName;
	
	document.getElementById("user_input").value = userName;
	
    let url = 'http://localhost:8080/request/';
    
    url = url + user_hold.innerText;
    

   
    let xhr = new XMLHttpRequest(); //readyState is 0
    console.log(xhr);


    //While the readyState is 3 or 4, we're just waiting around for the response. As such, we need to determine what will happen when we do receive a response.

    /*
        Each time the readyState changes, this callback we define will be invoked.
    */
    xhr.onreadystatechange = function(){
        // If the readyState is 4 and the HTTP status code is 200, I have access to the data I requested when I sent the HTTP request.
        if(xhr.readyState === 4 && xhr.status === 200){
            // My first order of business is to access the data itself. Data comes to us as JSON but that we want to be able to use the data as a JavaScript object.
            let requests = JSON.parse(xhr.response);

            console.log(requests);
            
            /*
                We need to iterate over this array of reimbursement objects in order to make a div for each reimbursement. We can use a for loop to accomplish this.

                Here we have used an enhanced for loop syntax.  
            */
			
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

            //Append the new div created to the existing reimbursement_container on  web page.

            request_container.appendChild(new_div);
            }
            console.log(user_hold.innerText);
        }
    }
    
    // Open HTTP request. We need to specify an HTTP verb and the URL.
    xhr.open('GET', url); //readyState is 1

    // Now let's send our HTTP request.
    xhr.send(); //readyState is 2
    
}

function logout() {

    localStorage.setItem("userName", null);
    
    window.location.replace("http://localhost:8080/LogIn.html");

}

// invoking a function as soon as window loads
window.onload = function(){
    this.getAllRequests();
}
