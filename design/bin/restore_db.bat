@echo off
set cdir=%cd%
echo start restore
mysql -uroot -proot node_eduoa<%cdir%/node_eduoa_dbbak.sql

echo stop restore