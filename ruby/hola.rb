#http://localhost:4567/
require 'sinatra'

get '/' do
  <<-HTML
  <!DOCTYPE html>
  <html lang="es">
  <head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar sesión</title>
  </head>
  <body>
    <h1>Iniciar sesión</h1>
    <form action="/login" method="post">
      <label for="username">Usuario:</label><br>
      <input type="text" id="username" name="username"><br>
      <label for="password">Contraseña:</label><br>
      <input type="password" id="password" name="password"><br><br>
      <input type="submit" value="Iniciar sesión">
    </form>
  </body>
  </html>
  HTML
end

# Definir una ruta para el manejo de la solicitud POST del formulario de inicio de sesión
post '/login' do
  username = params[:username]
  password = params[:password]
  
  # Aquí podrías agregar lógica para verificar las credenciales del usuario
  
  "¡Hola, #{username}! Tu contraseña es #{password}."
end