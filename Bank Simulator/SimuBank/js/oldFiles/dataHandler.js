function dataHandler(uri, data) {
    var xhr = new XMLHttpRequest();

    xhr.onreadystatechange = function () {
        if (this.readyState != 4) return;

        if (this.status == 200) {
            var data = JSON.parse(this.responseText);
            console.log(data); //testando      
        }
    };

    xhr.open('POST', uri, true);
    xhr.send(JSON.stringify(data))
}
