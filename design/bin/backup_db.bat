@echo off

title backup database
for /f "tokens=1 delims=/ " %%j in ("%date%") do set d1=%%j
for /f "tokens=2 delims=/ " %%j in ("%date%") do set d2=%%j
for /f "tokens=3 delims=/ " %%j in ("%date%") do set d3=%%j
for /f "tokens=1 delims=: " %%j in ("%time%") do set t1=%%j
for /f "tokens=2 delims=: " %%j in ("%time%") do set t2=%%j
for /f "tokens=3 delims=:. " %%j in ("%time%") do set t3=%%j
echo set datetime: %d1%-%d2%-%d3% %t1%:%t2%:%t3%
set currentDate=%d1%%d2%%d3%%t1%%t2%
set currentDir=%cd%
cd db_bak
set bak_file=node_eduoa_dbbak_%currentDate%.sql
set log_file=node_eduoa_dbbak.log
echo %date% >> %log_file% 
echo start backup database node_eduoa
mysqldump -uroot -proot node_eduoa > %currentDir%\db_bak\%bak_file% 
echo stop backup database node_eduoa 
echo %date% >> %log_file%
echo  >> %log_file%

cd ..
set cdir=%cd%
set dirPath=%currentDir%\db_bak\%bak_file% 
echo %dirPath% 
set destPath=%cdir%\node_eduoa_dbbak.sql
echo %destPath% 
echo start copy 
copy %dirPath% %destPath%
