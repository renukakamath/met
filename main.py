from flask import Flask
from public import public
from admin import admin
from master import master
from staff import staff
from api import api
app=Flask (__name__)
app.secret_key="123456"
app.register_blueprint(admin,url_prefix='/admin',)
app.register_blueprint(public)
app.register_blueprint(master,url_prefix='/master')
app.register_blueprint(staff,url_prefix='/staff')
app.register_blueprint(api,url_prefix='/api')

app.run (debug=True,port=5015)