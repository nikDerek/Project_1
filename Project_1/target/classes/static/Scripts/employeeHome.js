function getAllRequests(){
	
	let url = 'http://localhost:8080/request/username';
	let xhr = new XMLHttpRequest();
	
	let total = 0;
	let highAmount = 0;
	let highUser = null;
	let lowUser = Number.MAX_VALUE;
	
	xhr.onreadystatechange = function(){
        if(xhr.readyState === 4 && xhr.status === 200){
            let reqs = JSON.parse(xhr.response);

			let requests = document.getElementById('requests_table');
			let new_table = document.createElement('table');
			let row = document.createElement('tr');
			let rID =document.createElement("th");
			let rReason =document.createElement("th");
			let rAmount =document.createElement("th");
			let rStatus =document.createElement("th");
			rID.innerText = "Request ID";
			rReason.innerText = "Reason";
			rAmount.innerText = "Amount";
			rStatus.innerText = "Status";
			
			rID.id = "th";
			rReason.id = "th";
			rAmount.id = "th";
			rStatus.id = "th";
			
			new_table.appendChild(row);
			row.appendChild(rID);
			row.appendChild(rReason);
			row.appendChild(rAmount);
			row.appendChild(rStatus);
			requests.appendChild(new_table);
			
			console.log(reqs)
			
			for(let req of reqs){
				let requests =document.getElementById('requests_table');
				let new_table = document.createElement('table');
				let row = document.createElement('tr');
				let rID =document.createElement("th");
				let rReason =document.createElement("th");
				let rAmount =document.createElement("th");
				let rStatus =document.createElement("th");
				rID.innerText = req.requestId;
				rReason.innerText =req.request4;
				rAmount.innerText = req.requestAmount;
				rStatus.innerText = req.requestStatus;
				
				rID.id = "tr";
				rReason.id = "tr";
				rAmount.id = "tr";
				rStatus.id = "tr";
				
				new_table.appendChild(row);
				row.appendChild(rID);
				row.appendChild(rReason);
				row.appendChild(rAmount);
				row.appendChild(rStatus);
				requests.appendChild(new_table);
				
				total = total + pend.amount;
				
				if(pend.amount > highAmount){
					highAmount = pend.amount;
					highUser = pend.userName;
				}
				if(pend.amount > highAmount){
					highAmount = pend.amount;
					highUser = pend.userName;
				}
			}
			count = count+1;
		}
		console.log(total);
		letTotalh1 = document.createElement("h1");
		let newTotalTable = document.createElement('table');
	}
	xhr.open('Get', url);
	xhr.send();
}
function logout() {

    localStorage.setItem("userName", null);
    
    window.location.replace("http://localhost:8080/Login.html");

}


window.onload = getAllRequests();
