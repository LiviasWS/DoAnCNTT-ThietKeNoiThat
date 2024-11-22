function applyFilter() 
{
    const form = document.getElementById("filterForm");
    const formData = new FormData(form);
    const params = new URLSearchParams(formData);
    fetch("/ProductServlet?action=Filter&" + params.toString(), 
	{
      method: "GET",
    })
    .then(response => response.text())
    .catch(error => console.error("Error:", error));
}