let repository = []; 


function initiate() {

    repository = [
        { "insuranceID": "20023", "insuranceType": "Life Insurance", "insuranceName": "PlusLife", "insuranceKeyBenefits": "Coverage for critical illnesses, accidental death benefit, etc.", "insuranceLifetime": "30 years" },
        { "insuranceID": "20024", "insuranceType": "Health Insurance", "insuranceName": "MaxLife", "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.", "insuranceLifetime": "Unlimited" },
        { "insuranceID": "20024", "insuranceType": "wealth Insurance", "insuranceName": "MaxLife", "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.", "insuranceLifetime": "Unlimited" },
        { "insuranceID": "20024", "insuranceType": "car Insurance", "insuranceName": "MaxLife", "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.", "insuranceLifetime": "Unlimited" },
        { "insuranceID": "20024", "insuranceType": "head Insurance", "insuranceName": "MaxLife", "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.", "insuranceLifetime": "Unlimited" },
        { "insuranceID": "20024", "insuranceType": "car Insurance", "insuranceName": "MaxLife", "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.", "insuranceLifetime": "Unlimited" },
        { "insuranceID": "20024", "insuranceType": "Health Insurance", "insuranceName": "MaxLife", "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.", "insuranceLifetime": "Unlimited" },
        { "insuranceID": "20024", "insuranceType": "Health Insurance", "insuranceName": "MaxLife", "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.", "insuranceLifetime": "Unlimited" },
        { "insuranceID": "20024", "insuranceType": "Health Insurance", "insuranceName": "MaxLife", "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.", "insuranceLifetime": "Unlimited" },
        { "insuranceID": "20024", "insuranceType": "Health Insurance", "insuranceName": "MaxLife", "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.", "insuranceLifetime": "Unlimited" },
        { "insuranceID": "20024", "insuranceType": "Health Insurance", "insuranceName": "MaxLife", "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.", "insuranceLifetime": "Unlimited" },


    ];
}


function renderPage(pageNumber, pageSize) {
    let startIndex = (pageNumber - 1) * pageSize;
    let endIndex = startIndex + pageSize;
    let content = "";

    for (let i = startIndex; i < endIndex && i < repository.length; i++) {
        let insurance = repository[i];
        content += `<div class="col">
                        <div class="card text-white bg-warning btn btn-outline-dark p-2 me-4 shadow-lg" data-bs-toggle="modal" data-bs-target="#cardModal${i}">
                            <div class="card-header">${insurance.insuranceType}</div>
                            <div class="card-body card-content">
                                <h5 class="card-title">${insurance.insuranceName}</h5>
                                <p class="card-text">${insurance.insuranceKeyBenefits}</p>
                            </div>
                        </div>
                    </div>`;


        content += `<div class="modal fade" id="cardModal${i}" tabindex="-1" aria-labelledby="cardModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="cardModalLabel">Detailed View</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="card text-white bg-warning">
                                        <div class="card-header">${insurance.insuranceType}</div>
                                        <div class="card-body">
                                            <h3 class="card-title">${insurance.insuranceName}</h3>
                                            <h5 class="card-body">
                                                <p class="card-text font-monospace">Insurance ID: ${insurance.insuranceID}</p>
                                                <p class="card-text font-monospace">Insurance Type: ${insurance.insuranceType}</p>
                                                <p class="card-text font-monospace">Key Benefits: ${insurance.insuranceKeyBenefits}</p>
                                                <p class="card-text font-monospace text-dark">Insurance Lifetime: ${insurance.insuranceLifetime}</p>
                                            </h5>
                                            <button type="button" onclick="window.location.href='../html/AvailInsurance.html'" class="btn btn-primary">Apply Insurance</button>
                                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>`;
    }


    document.getElementById("insuranceCards").innerHTML = content;
}


function searchByInsuranceType() {
    let searchInput = document.getElementById("searchInput").value.toLowerCase();
    let searchResults = repository.filter(insurance => insurance.insuranceType.toLowerCase().includes(searchInput));

    renderSearchResults(searchResults);
}


function renderSearchResults(results) {
    let content = "";
    results.forEach((insurance, index) => {
        content += `<div class="col">
                        <div class="card text-white bg-warning btn btn-outline-dark p-2 me-4 shadow-lg" data-bs-toggle="modal" data-bs-target="#cardModal${index}">
                            <div class="card-header">${insurance.insuranceType}</div>
                            <div class="card-body card-content">
                                <h5 class="card-title">${insurance.insuranceName}</h5>
                                <p class="card-text">${insurance.insuranceKeyBenefits}</p>
                            </div>
                        </div>
                    </div>`;


        content += `<div class="modal fade" id="cardModal${index}" tabindex="-1" aria-labelledby="cardModalLabel" aria-hidden="true">
                        <div class="modal-dialog modal-lg">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="cardModalLabel">Detailed View</h5>
                                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                                </div>
                                <div class="modal-body">
                                    <div class="card text-white bg-warning">
                                        <div class="card-header">${insurance.insuranceType}</div>
                                        <div class="card-body">
                                            <h3 class="card-title">${insurance.insuranceName}</h3>
                                            <h5 class="card-body">
                                                <p class="card-text font-monospace">Insurance ID: ${insurance.insuranceID}</p>
                                                <p class="card-text font-monospace">Insurance Type: ${insurance.insuranceType}</p>
                                                <p class="card-text font-monospace">Key Benefits: ${insurance.insuranceKeyBenefits}</p>
                                                <p class="card-text font-monospace text-dark">Insurance Lifetime: ${insurance.insuranceLifetime}</p>
                                            </h5>
                                            <button type="button" onclick="window.location.href='../html/AvailInsurance.html'" class="btn btn-primary">Apply Insurance</button>
                                            </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>`;
    });


    document.getElementById("insuranceCards").innerHTML = content;
}


function paginate() {
    let pageNumber = 1;
    const pageSize = 4; 


    renderPage(pageNumber, pageSize);


    document.getElementById("nextPage").addEventListener("click", () => {
        pageNumber++;
        renderPage(pageNumber, pageSize);
    });


    document.getElementById("prevPage").addEventListener("click", () => {
        if (pageNumber > 1) {
            pageNumber--;
            renderPage(pageNumber, pageSize);
        }
    });
}


initiate();

paginate();


let totalCards = 20; 

let currentPage = 1;
const cardsPerPage = 4; 


function updatePageNumber() {
    document.getElementById("pageNumber").innerText = `Page ${currentPage}`;
}

document.getElementById("prevPage").addEventListener("click", function() {
    if (currentPage > 1) {
        currentPage--;
        updatePageNumber();

        displayInsuranceCards();
    }
});


document.getElementById("nextPage").addEventListener("click", function() {
    const totalPages = Math.ceil(totalCards / cardsPerPage);
    if (currentPage < totalPages) {
        currentPage++;
        updatePageNumber();
        displayInsuranceCards();
    }
});

