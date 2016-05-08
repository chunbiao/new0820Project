window.onload = inputAction;

function inputAction(){
	var listFrame = window.document.getElementById("listFrame");
	if(listFrame.addEventListener){
		listFrame.addEventListener("click",listChange,false);
	}else if(listFrame.attachEvent){
		listFrame.attachEvent("onclick",listChange);
	}
}
function listChange(event){
	if(event.target.parentNode.className=="listTitle"){
		if(event.target.parentNode.childNodes[3].style.display == "block")
			event.target.parentNode.childNodes[3].style.display = "none";
		else event.target.parentNode.childNodes[3].style.display = "block";
	}
	/* if(event.target.type == "checkbox"){
		var nodeList = event.target.parentNode.childNodes;
		nodeList.forEach(function(item,index,array){
			if(item.nodeName == "INPUT")item.checked = "true";
		});
	} */
}