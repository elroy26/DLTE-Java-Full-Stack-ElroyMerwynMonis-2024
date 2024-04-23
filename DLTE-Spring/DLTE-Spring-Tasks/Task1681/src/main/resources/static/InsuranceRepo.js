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
// Function to render all insurance cards
function renderInsuranceCards() {
    let content = "";

    repository.forEach((insurance, i) => {
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
                                            <button type="button" th:attr="data-href=@{/AvailInsurance}" class="btn btn-primary">Apply Insurance</button>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>`;
    });

    document.getElementById("insuranceCards").innerHTML = content;
}