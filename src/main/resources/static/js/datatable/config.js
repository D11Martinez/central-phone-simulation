$(function() {
	$("#data_table")
			.DataTable(
					{
						"responsive" : true,
						"autoWidth" : false,
						"language" : {
							"decimal" : "",
							"emptyTable" : "No hay datos disponibles en la tabla",
							"info" : "Mostrando del _START_ al _END_ de _TOTAL_ registros",
							"infoEmpty" : "Mostrando del 0 al 0 de 0 registros",
							"infoFiltered" : "(filtrado de _MAX_ registros totales)",
							"infoPostFix" : "",
							"thousands" : ",",
							"lengthMenu" : "Mostrar _MENU_ registros",
							"loadingRecords" : "Cargando...",
							"processing" : "Procesando...",
							"search" : "Buscar",
							"zeroRecords" : "No se encontraron registros coincidentes",
							"paginate" : {
								"first" : "Primera",
								"last" : "Última",
								"next" : "Siguiente",
								"previous" : "Anterior"
							},
							"aria" : {
								"sortAscending" : ": activar para ordenar la columna ascendentemente",
								"sortDescending" : ": activar para ordenar la columna descendentemente"
							}
						}
					});
});