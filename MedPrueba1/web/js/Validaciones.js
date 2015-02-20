/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 function Letras(e)
    {
        var keynum;

        if (window.event)
            {
                keynum = e.keyCode;
            }
        else                    
            if (e.which)
                {                    
                    keynum = e.which;
                }

        if ((keynum >= 97 && keynum <= 122) || (keynum >= 65 && keynum <= 90) || (keynum == 8) || (keynum == 32) || (keynum >= 160 && keynum <= 163))
            {
                return true;
            }
        else
            {
                return false;
            }
    }
            
   


    function Numeros(e)
        {
            var keynum;
            if (window.event)
                {
                    keynum = e.keyCode;
                }
            else
                if (e.which)
                    {
                        keynum = e.which;
                    }

            if ((keynum >= 48 && keynum <= 57))
                {
                    return true;
                }      
            else
                {
                    return false;
                }
        }


