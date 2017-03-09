/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function login(x) { 
	$.get('LoginControl.do', function(data) {
        alert(data);
    });
}
