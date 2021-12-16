<?php
/*
student
Attributes
-name
-Index no
-Sex
-Date of birth
-School
-scores array("English"->45,"SST"->45,"Kiswahili"->45,"Social studies"->45,"Science"->45) - to be added in html
-SchoolChoice array("choice1"->am1,"choice2"->am2,"choice3"->am3,)
*/
//connect to the database
$mysqli = new mysqli('localhost','root','mypass123','crud') or die(mysqli_error($mysqli));

//receive data
if(isset($_POST['save'])){
    $Indexno = $_POST["index"];
    $name = $_POST["nam"];
    $sex  = $_POST["sex"];
    $dob = $_POST["dob"];
    $school = $_POST["school"];

//Store the data to the database
$mysqli->query(INSERT INTO data(index,name,sex,dob,school) values('$Indexno','$name','$sex','$dob','$school'))
or die($mysqli->error);
}


//validate data
//Display data received

//function to select students and display a list of selected students
function SelAndDisp($aggregate,$choice[]){

}
//A student can only be selected by two schools
?>

