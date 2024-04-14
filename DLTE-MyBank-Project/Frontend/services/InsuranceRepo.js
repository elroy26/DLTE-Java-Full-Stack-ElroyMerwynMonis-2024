function initiate() {
    // Populate repository with insurance data
    let repository = [
        {   "insuranceID": "20023",
            "insuranceType": "Life Insurance",
            "insuranceName": "PlusLife",
            "insuranceKeyBenefits": "Coverage for critical illnesses, accidental death benefit, etc.",
            "insuranceLifetime": "30 years"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        },
        {
            "insuranceID": "20023",
            "insuranceType": "Health Insurance",
            "insuranceName": "MaxLife",
            "insuranceKeyBenefits": "Hospitalization coverage, medical expenses reimbursement, etc.",
            "insuranceLifetime": "Unlimited"
        }
        
    ];

    // Store repository data in local storage
    localStorage.setItem('insuranceData', JSON.stringify(repository));
}

// Call the initiate function to populate the repository
initiate();

function iterateInsurance() {
    let repository = JSON.parse(localStorage.getItem('insuranceData'));
    let content = "";

    repository.forEach((insurance, index) => {
        content += `<div class="col">
                        <div class="card text-white bg-warning btn btn-outline-dark p-2 me-4 shadow-lg" data-bs-toggle="modal" data-bs-target="#cardModal${index}">
                            <div class="card-header">${insurance.insuranceType}</div>
                            <div class="card-body card-content">
                                <h5 class="card-title">${insurance.insuranceName}</h5>
                                <p class="card-text">${insurance.insuranceKeyBenefits}</p>
                            </div>
                        </div>
                    </div>`;

        // Generate modal for detailed view
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

    // Display insurance cards
    document.getElementById("insuranceCards").innerHTML = content;
}

// Generate insurance cards
iterateInsurance();