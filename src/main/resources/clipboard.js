 function docReady(fn) {
     // see if DOM is already available
     if (document.readyState === "complete" || document.readyState === "interactive") {
         // call on next available tick
         setTimeout(fn, 1);
     } else {
         document.addEventListener("DOMContentLoaded", fn);
     }
 }

 docReady(function() {
     var elems = document.getElementsByClassName('listingblock acopy');
     //console.log("****** elems.length " + elems.length)
     for (var i = 0; i < elems.length; i++) {
         var pre = elems[i].getElementsByTagName('pre')[0]
         //console.log("****** pres.length " + pres.length)
         var b = document.createElement('button');
         b.className = 'adocclip';
         b.setAttribute("data-clipboard-target", "#copyadoc"+i)
         b.innerHTML = '&nbsp;';
         pre.setAttribute("id", "copyadoc"+i)
         pre.parentNode.prepend(b);
     }
     var clipboard = new ClipboardJS('.adocclip')
     clipboard.on('success', function(e) {
        //console.info('Action:', e.action);
        //console.info('Text:', e.text);
        //console.info('Trigger:', e.trigger);
        e.clearSelection();
        e.trigger.innerHTML = '&nbsp;&nbsp;&#10003;';
	    setTimeout(function() {
	         //e.trigger.classList.remove("adocclippress");
	         e.trigger.innerHTML = '&nbsp;';
	    }, 500);
     });
 });
