$(function() {

	$('#mx-table tbody').remove();
	
	for (var i = 1; i <= 10; i++) {
		var row = [
			'<tr>',
	        	'<td class="check">',
	            	'<input id="check_' + i + '" name="check_' + i + '" type="checkbox" value="1" />',
	        	'</td>',
	        	'<td>Some Content</td>',
				'<td>' + i + '</th>',
	    	'</tr>'
		];
		$(row.join('')).appendTo('#mx-table');
	}

});