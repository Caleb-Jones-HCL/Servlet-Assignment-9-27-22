<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Servlet Assignment 9/27/22</title>
<style>.hidden {

        display: none;
    }
</style>
</head>
<body>
    <h2>ACTIONS:</h2>

    <button onClick="toggleEdit()">EDIT</button>
    <form id="hideEdit" class="hidden" action="UpdateResponseServlet" method="post">
        <table  style="with: 80%" >
            <tr>
                <th>Search Employee Number:</th>
                <td><input type="text" name="editEmpNum" /></td>
            </tr>
            <tr>
                <td>Update First Name</td>
                <td><input type="text" name="editFirstName" /></td>
            </tr>
            <tr>
                <td>Update Last Name</td>
                <td><input type="text" name="editLastName" /></td>
            </tr>
        </table>
        <input type="submit" value="Update Employee" />
    </form>

    <br><br>

    <button onClick="toggleDel()">DELETE</button>
    <form id="hideDel" class="hidden" action="DeleteResponseServlet" method="post">
        <table  style="with: 80%" >
            <tr>
                <th>Input values to search for a record to delete:</th> 
            </tr>
            <tr>
                <td>Employee Number</td>
                <td><input type="text" name="delEmpNum" /></td>
            </tr>
            <tr>
                <td>First Name</td>
                <td><input type="text" name="delFirstName" /></td>
            </tr>
            <tr>
                <td>Last Name</td>
                <td><input type="text" name="delLastName" /></td>
            </tr>
        </table>
        <input type="submit" value="Delete Employee" />
    </form>

    <div align="center">
        <h1>Add Employee Information</h1>
        <form action="sendQuery" method="post">
            <table style="with: 80%">

                <tr>
                    <td>Employee Number</td>
                    <td><input type="text" name="empNum" /></td>
                </tr>
                <tr>
                    <td>First Name</td>
                    <td><input type="text" name="firstName" /></td>
                </tr>
                <tr>
                    <td>Last Name</td>
                    <td><input type="text" name="lastName" /></td>
                </tr>
            </table>
            <input type="submit" value="Insert Employee" />
        </form>
    </div>




    
<script>
function toggleEdit() {

  document.getElementById("hideEdit").classList.toggle("hidden");

}
function toggleDel() {

document.getElementById("hideDel").classList.toggle("hidden");

}
</script>
	
	
</body>
</html>