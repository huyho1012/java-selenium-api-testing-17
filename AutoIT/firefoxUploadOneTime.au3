WinWait("File Upload", "", "30")
If WinExists("File Upload") Then
	  Sleep(1500);
	  ControlSetText("File Upload", "", "Edit1", $CmdLine[1]);
	  Sleep(1500);
      ControlClick("File Upload", "", "&Open");
EndIf