function compileCode() {
    const javaCode = document.getElementById('javaCode').value;

    fetch('/compile', {
        method: 'POST',
        headers: { 'Content-Type': 'text/plain' },
        body: javaCode
    })
    .then(response => response.text())
    .then(output => document.getElementById('output').innerText = output)
    .catch(error => document.getElementById('output').innerText = 'Error: ' + error);
}
