 
    var req;
    var isIE;
    var completeField;
    var completeTable;
    var autoRow;

    var matchingEmps;


     $(document).ready(function () {
        completeField = document.getElementById("complete-field");
        completeTable = document.getElementById("complete-table");
        autoRow = document.getElementById("auto-row");
        completeTable.style.top = getElementY(autoRow) + "px";
    });

    function doCompletion() {
        var url = "searchStaffingEmp?action=complete&id=" + escape(completeField.value);
        req = initRequest();
        req.open("GET", url, true);
        req.onreadystatechange = callback;
        req.send(null);
    }

    function initRequest() {
        if (window.XMLHttpRequest) {
            if (navigator.userAgent.indexOf('MSIE') != -1) {
                isIE = true;
            }
            return new XMLHttpRequest();
        } else if (window.ActiveXObject) {
            isIE = true;
            return new ActiveXObject("Microsoft.XMLHTTP");
        }
    }

    function callback() {
        clearTable();
        if (req.readyState == 4) {
            if (req.status == 200) {
                matchingEmps = JSON.parse(req.responseText);   
                fillTable();
            }
        }
    }

    function appendEmployee(firstName, lastName, employeeId) {
        var row;
        var cell;
        var linkElement;
        if (isIE) {
            completeTable.style.display = 'block';
            row = completeTable.insertRow(completeTable.rows.length);
            cell = row.insertCell(0);
        } else {
            completeTable.style.display = 'table';
            row = document.createElement("tr");
            cell = document.createElement("td");
            row.appendChild(cell);
            completeTable.appendChild(row);
        }

        cell.className = "popupCell";

        linkElement = document.createElement("a");
        linkElement.className = "popupItem";
        linkElement.setAttribute("href", "searchStaffingEmp?action=lookup&id=" + employeeId);
        linkElement.appendChild(document.createTextNode(firstName + " " + lastName));
        cell.appendChild(linkElement);
    }

    function clearTable() {
        if (completeTable.getElementsByTagName("tr").length > 0) {
            completeTable.style.display = 'none';
            for (loop = completeTable.childNodes.length - 1; loop >= 0; loop--) {
                completeTable.removeChild(completeTable.childNodes[loop]);
            }
        }
    }

    function getElementY(element) {

        var targetTop = 0;

        if (element.offsetParent) {
            while (element.offsetParent) {
                targetTop += element.offsetTop;
                element = element.offsetParent;
            }
        } else if (element.y) {
            targetTop += element.y;
        }
        return targetTop;
    }

    function fillTable() {

        completeTable.setAttribute("bordercolor", "black");
        completeTable.setAttribute("border", "1");
        var i;
    for(i = 0; i < matchingEmps.length; i++)
            appendEmployee(matchingEmps[i].firstname, matchingEmps[i].lastname, matchingEmps[i].id);
    }


