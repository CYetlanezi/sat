/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function cargarSelect2(valor)
		{		
			var arrayValores=new Array(
			new Array(1,1,"opcion1-1"),
			new Array(1,2,"opcion1-2"),
			new Array(1,3,"opcion1-3"),
			new Array(2,1,"opcion2-1"),
			new Array(3,1,"opcion3-1"),
			new Array(3,2,"opcion3-2"),
			new Array(3,3,"opcion3-3"),
			new Array(3,4,"opcion3-4")
		);
		
    if(valor==0)
		{        
			document.getElementById("select2").disabled=true;
		}
	else
		{        
		document.getElementById("select2").options.length=0;
		document.getElementById("select2").options[0]=new Option("Selecciona una opcion", "0");
		for(i=0;i<arrayValores.length;i++)
				{            
				if(arrayValores[i][0]==valor)
					{
						document.getElementById("select2").options[document.getElementById("select2").options.length]=new Option(arrayValores[i][2], arrayValores[i][1]);
				}
			}         
	document.getElementById("select2").disabled=false;
		}
	}

	function seleccinado_select2(value)
	{
		var v1 = document.getElementById("select1");
		var valor1 = v1.options[v1.selectedIndex].value;
		var text1 = v1.options[v1.selectedIndex].text;
		var v2 = document.getElementById("select2");
		var valor2 = v2.options[v2.selectedIndex].value;
		var text2 = v2.options[v2.selectedIndex].text;		
		alert("Se ha seleccionado el valor "+valor1+" ("+text1+") del primer select y el valor "+valor2+" ("+text2+") del segundo select");
        }
