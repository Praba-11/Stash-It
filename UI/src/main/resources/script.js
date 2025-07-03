// Sample member data for demonstration
const members = [
    { id: 1, rollNo: "MEM001", name: "John Doe", email: "john.doe@company.com", department: "IT", position: "Developer" },
    { id: 2, rollNo: "MEM002", name: "Jane Smith", email: "jane.smith@company.com", department: "HR", position: "Manager" },
    { id: 3, rollNo: "MEM003", name: "Mike Johnson", email: "mike.johnson@company.com", department: "Finance", position: "Analyst" },
    { id: 4, rollNo: "MEM004", name: "Sarah Wilson", email: "sarah.wilson@company.com", department: "Marketing", position: "Coordinator" },
    { id: 5, rollNo: "MEM005", name: "Tom Brown", email: "tom.brown@company.com", department: "Operations", position: "Supervisor" }
];

let selectedMember = null;

// Tab switching functionality
document.querySelectorAll('.member-type-btn').forEach(btn => {
    btn.addEventListener('click', function () {
        const type = this.dataset.type;

        document.querySelectorAll('.member-type-btn').forEach(b => b.classList.remove('active'));
        this.classList.add('active');

        document.querySelectorAll('.form-section').forEach(section => section.classList.remove('active'));

        document.getElementById(type === 'new' ? 'newMemberSection' : 'existingMemberSection').classList.add('active');
    });
});

// Member search functionality
const memberSearch = document.getElementById('memberSearch');
const memberDropdown = document.getElementById('memberDropdown');
const selectedMemberInfo = document.getElementById('selectedMemberInfo');

memberSearch.addEventListener('input', function () {
    const query = this.value.toLowerCase();

    if (query.length < 2) {
        memberDropdown.classList.remove('show');
        return;
    }

    const filtered = members.filter(m =>
        m.rollNo.toLowerCase().includes(query) ||
        m.name.toLowerCase().includes(query) ||
        m.email.toLowerCase().includes(query)
    );

    memberDropdown.innerHTML = '';
    if (filtered.length > 0) {
        filtered.forEach(member => {
            const item = document.createElement('div');
            item.className = 'dropdown-item';
            item.innerHTML = `
                <div><strong>${member.rollNo} - ${member.name}</strong></div>
                <div style="font-size: 0.9em; color: #666;">${member.email} - ${member.department}</div>
            `;
            item.addEventListener('click', () => selectMember(member));
            memberDropdown.appendChild(item);
        });
        memberDropdown.classList.add('show');
    } else {
        memberDropdown.classList.remove('show');
    }
});

document.addEventListener('click', function (e) {
    if (!e.target.closest('.search-container')) {
        memberDropdown.classList.remove('show');
    }
});

function selectMember(member) {
    selectedMember = member;
    memberSearch.value = `${member.rollNo} - ${member.name}`;
    memberDropdown.classList.remove('show');

    document.getElementById('selectedMemberRoll').textContent = member.rollNo;
    document.getElementById('selectedMemberName').textContent = member.name;
    document.getElementById('selectedMemberEmail').textContent = member.email;
    document.getElementById('selectedMemberDept').textContent = member.department;
    document.getElementById('selectedMemberPos').textContent = member.position;
    selectedMemberInfo.style.display = 'block';
}

// File handling for new member form
const artifactFile = document.getElementById('artifactFile');
const filePreview = document.getElementById('filePreview');
const fileName = document.getElementById('fileName');

artifactFile.addEventListener('change', function () {
    if (this.files && this.files[0]) {
        fileName.textContent = this.files[0].name;
        filePreview.classList.add('show');
    } else {
        filePreview.classList.remove('show');
    }
});

// File handling for existing member form
const existingArtifactFile = document.getElementById('existingArtifactFile');
const existingFilePreview = document.getElementById('existingFilePreview');
const existingFileName = document.getElementById('existingFileName');

existingArtifactFile.addEventListener('change', function () {
    if (this.files && this.files[0]) {
        existingFileName.textContent = this.files[0].name;
        existingFilePreview.classList.add('show');
    } else {
        existingFilePreview.classList.remove('show');
    }
});

// Set default date to today
document.getElementById('newCreatedDate').valueAsDate = new Date();
document.getElementById('createdDate').valueAsDate = new Date();

// Form submission
document.getElementById('newMemberForm').addEventListener('submit', function (e) {
    e.preventDefault();
    showNotification('Member created and artifact stored successfully!', 'success');
    this.reset();
    filePreview.classList.remove('show');
    // Reset default date
    document.getElementById('newCreatedDate').valueAsDate = new Date();
});

document.getElementById('existingMemberForm').addEventListener('submit', function (e) {
    e.preventDefault();
    if (!selectedMember) {
        showNotification('Please select a member first!', 'error');
        return;
    }
    showNotification('Artifact stored for member successfully!', 'success');
    this.reset();
    existingFilePreview.classList.remove('show');
    selectedMemberInfo.style.display = 'none';
    selectedMember = null;
    // Reset default date
    document.getElementById('createdDate').valueAsDate = new Date();
});

function showNotification(message, type) {
    const notification = document.createElement('div');
    notification.className = `notification ${type}`;
    notification.textContent = message;
    document.body.appendChild(notification);

    setTimeout(() => notification.classList.add('show'), 100);
    setTimeout(() => {
        notification.classList.remove('show');
        setTimeout(() => document.body.removeChild(notification), 300);
    }, 3000);
}