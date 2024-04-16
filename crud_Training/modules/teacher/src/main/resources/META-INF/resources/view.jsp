<%@ include file="init.jsp" %>

<aui:form id="addTeacher">

	<aui:input name="name" type="text" placeholder="Enter your name" label="FULL NAME" required="true"/>
	<aui:input name="address" type="text" placeholder="Address" label="ADDRESS" required="true"/>
	<aui:button type="submit" value="ADD"/>

</aui:form>

<script>
	document.getElementById("addTeacher").addEventListener("submit",function(event){
		event.preventDefault();
		
		var name = document.getElementById("name").value;
		var address = document.getElementById("address").value;
		
		fetch('/o/teacher-headless-tracker',{
			method: 'post',
			headers: {
				'Content-Type': 'application/json',
				'Authorization': ''
			},
			body: JSON.stringify({
				name: name,
				address: address
			})
		})
		.then(response => response.json())
		.then(data => {
			console.log(data);
		})
		.catch(error =>{
			console.error('Error', error);
		});
	});
</script>
