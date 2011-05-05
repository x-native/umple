<?php
require_once("ui/framework.php");

if (isset($_POST["buttonNext"]))
{
	$building = retrieveBuilding();
  
	if (isset($_POST["inputAction"]) && $_POST["inputAction"] != "")
	{
		handleActions($building, $_POST["inputAction"]);
	}
	$building->nextStep();
	$autoChecked = isset($_POST["inputAutomatic"]) ? "checked" : ""; 
}
else
{
	$building = createBuilding();
	$autoChecked = "";
}
assignRandomFloor($building);

?>
<html>
<head>
<style>
table.elevatorshaft, table.elevatorshaft tr {padding:0;margin:0;}
table.elevatorshaft td {width:50px;height:100px;background-color:#7E8B8B;}
div.open {background-color:blue;width:50%;height:100%;}
table.elevatorshaft td.rightdoor {text-align:right;}

table.elevatorshaft div.rightdoor {float:right;background-color:#C0C0C0;width:48%;height:100%}
table.elevatorshaft div.leftdoor {float:left;background-color:#C0C0C0;width:48%;height:100%}

table.elevatorshaft td.buttons {background-color:white; width:10px;}
table.elevatorshaft td.people {background-color:white; width:500px;vertical-align:bottom;}

img.button {cursor:pointer;}

</style>
<script type="text/javascript" src="ui/display.js"></script>

</head>
<body>

Welcome to <?php echo $building->getName() ?><br />

<?php displayElevatorShaft($building); ?>

<br /><br />
<form action="index.php" method="post">
<input id="inputAutomatic" name="inputAutomatic" type="checkbox" value="auto" <?php echo$autoChecked?> /> On Automatic<br />
<input id="buttonNext" name="buttonNext" type="submit" value="next" />
<input id="buttonReset" name="buttonReset" type="submit" value="reset" />
<br />
<?php storeBuilding($building); ?>
<input id="inputAction" name="inputAction" type="hidden" />
</form>
<script language="javascript">

var inputAutomatic = document.getElementById("inputAutomatic");
var buttonNext = document.getElementById("buttonNext");

if (inputAutomatic.checked)
{
	setTimeout("buttonNext.click()",300);
}

</script>
</body>
</html>