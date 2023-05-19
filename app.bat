@ECHO OFF
cd bin
echo ----- Informatii imagine 1 -----
java MainPackage.Main ..\test\test1.bmp ..\test\out1.bmp
echo ----- Informatii imagine 2 -----
java MainPackage.Main ..\test\test2.bmp ..\test\out2.bmp
PAUSE