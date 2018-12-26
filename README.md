# TeSigoAPI

## Backend correspondiente al proyecto de tesis

## Servicios REST
### Obtener el seguimiento de un alumno en una unidad determinada (GET)
`http://206.189.195.214:8080/api/unidad/{idUnidad}/alumno/{idAlumno}`
### Actualizar estado de un indicador para un alumno en específico (PUT)
`http://206.189.195.214:8080/api/acompletado/update/{idIndicador}/{idAlumno}`
### Obtener los cursos de un profesor (GET)
`http://206.189.195.214:8080/api/profesor/{idProfesor}/cursos`
### Insertar un nuevo profesor en la base de datos (POST)
`http://206.189.195.214:8080/api/profesor`
```javascript
Request body
{
   "nombreProfesor" : "Ariel",
   "apellidoPaterno" : "Tirado",
   "apellidoMaterno" : "Maturana",
   "correoElectronico" : "ariel.tirado@usach.cl",
   "firebaseUID" : "Qt5i8i3Mk2U3P1fbo1djhV8bHB74"
}
```
### Obtener el listado de alumnos de un curso (GET)
`http://206.189.195.214:8080/api/curso/{idCurso}/alumnos`
### Obtener el listado de unidades de un curso (GET)
`http://206.189.195.214:8080/api/curso/{idCurso}/unidades`
### Actualizar el estado de un serie de indicadores para un alumno en específico
`http://206.189.195.214:8080/api/acompletado/update/{idAlumno}/indicadores`
```javascript
Request body
[
    {
        "idIndicador" : 1,
        "status" : true
    },
    {
        "idIndicador" : 2,
        "status" : false
    }
]
```
### Obtener todos los formularios de evidencias de un alumno específico (GET)
`http://206.189.195.214:8080/api/formularioEvidencia/alumno/{idAlumno}`
### Agregar un nuevo formulario de evidencia para un alumno específico (POST)
`http://206.189.195.214:8080/api/formularioEvidencia/alumno/{idAlumno}`
```javascript
Request body
{
    "nombreEvidencia": "Cabro haciendo algo fome",
    "contextoEvidencia": "Vi al cabro haciendo algo fome",
    "fechaEvidencia": "2018-12-26T15:45:30.000+0000",
    "firebaseID": null
}
```