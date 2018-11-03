# TeSigoAPI

## Backend correspondiente al proyecto de tesis

## Servicios REST
### Obtener el seguimiento de un alumno en una unidad determinada (GET)
`http://localhost:8082/api-tesis/unidad/{idUnidad}/alumno/{idAlumno}`
### Actualizar estado de un indicador para un alumno en específico (PUT)
`http://localhost:8082/api-tesis/acompletado/update/{idIndicador}/{idAlumno}`
### Obtener los cursos de un profesor (GET)
`http://localhost:8082/api-tesis/profesor/{idProfesor}/cursos`
### Insertar un nuevo profesor en la base de datos (POST)
`http://localhost:8082/api-tesis/profesor`
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
`http://localhost:8082/api-tesis/curso/{idCurso}/alumnos`
### Obtener el listado de unidades de un curso (GET)
`http://localhost:8082/api-tesis/curso/{idCurso}/unidades`
### Actualizar el estado de un serie de indicadores para un alumno en específico (PUT)
`http://localhost:8082/api-tesis/acompletado/update/{idAlumno}/indicadores`
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

