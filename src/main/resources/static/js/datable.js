
  $(function () {
    $("#tabla").DataTable({
      "responsive": true,
      "autoWidth": false,
      
      "language": {
			"emptyTable":			"No hay datos disponibles en la tabla.",
			"info":		   		"Del _START_ al _END_ de _TOTAL_ ",
			"infoEmpty":			"Mostrando 0 registros de un total de 0.",
			"infoFiltered":			"(filtrados de un total de _MAX_ registros)",
			"infoPostFix":			"(actualizados)",
			"lengthMenu":			"Mostrar _MENU_ registros",
			"loadingRecords":		"Cargando...",
			"processing":			"Procesando...",
			"search":			"Buscar:",
			"searchPlaceholder":		" ",
			"zeroRecords":			"No se han encontrado coincidencias.",
			"paginate": {
				"first":			"Primera",
				"last":				"Última",
				"next":				"Siguiente",
				"previous":			"Anterior"
			},
			"aria": {
				"sortAscending":	"Orden ascendente",
				"sortDescending":	"Orden descendente"
			}
		},
		
    });
  });
  
  
    
   


