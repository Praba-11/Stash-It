// -------------------------------
// Globals
// -------------------------------
let selectedMember = null;
let memberCache = [];

// -------------------------------
// DOM Ready
// -------------------------------
document.addEventListener('DOMContentLoaded', () => {
  initTabSwitching();
  initMemberSearch();
  initFileInputs();
  initFormHandlers();
  initDateDefaults();
  fetchDepartments();
  fetchArtifactTypes();
  fetchAllMembers();
});

// -------------------------------
// Tab Switching
// -------------------------------
function initTabSwitching() {
  const buttons = document.querySelectorAll('.member-type-btn');
  buttons.forEach(btn => {
    btn.addEventListener('click', () => {
      const selected = btn.dataset.type;

      buttons.forEach(b => b.classList.remove('active'));
      btn.classList.add('active');

      document.querySelectorAll('.form-section').forEach(section => {
        section.classList.remove('active');
      });

      document.getElementById(selected === 'new' ? 'newMemberSection' : 'existingMemberSection').classList.add('active');
    });
  });
}

// -------------------------------
// Fetch All Members from Backend
// -------------------------------
function fetchAllMembers() {
  fetch('/api/member/find/all')
    .then(res => {
      if (!res.ok) throw new Error('Failed to fetch members');
      return res.json();
    })
    .then(data => {
      memberCache = data;
    })
    .catch(err => console.error('Error fetching members:', err));
}

// -------------------------------
// Member Search
// -------------------------------
function initMemberSearch() {
  const input = document.getElementById('memberSearch');
  const dropdown = document.getElementById('memberDropdown');
  const infoBox = document.getElementById('selectedMemberInfo');

  input.addEventListener('input', () => {
    const query = input.value.toLowerCase();
    if (query.length < 2 || memberCache.length === 0) {
      dropdown.classList.remove('show');
      return;
    }

    const filtered = memberCache.filter(m =>
      m.rollNo.toLowerCase().includes(query) ||
      m.firstName.toLowerCase().includes(query) ||
      m.lastName.toLowerCase().includes(query) ||
      m.emailAddress.toLowerCase().includes(query)
    );

    dropdown.innerHTML = '';
    if (filtered.length > 0) {
      filtered.forEach(member => {
        const item = document.createElement('div');
        item.className = 'dropdown-item';
        item.innerHTML = `
          <div><strong>${member.rollNo} - ${member.firstName} ${member.lastName}</strong></div>
          <div style="font-size: 0.9em; color: #666;">${member.emailAddress} - ${member.department}</div>
        `;
        item.addEventListener('click', () => selectMember(member));
        dropdown.appendChild(item);
      });
      dropdown.classList.add('show');
    } else {
      dropdown.classList.remove('show');
    }
  });

  document.addEventListener('click', e => {
    if (!e.target.closest('.search-container')) {
      dropdown.classList.remove('show');
    }
  });

  function selectMember(member) {
    selectedMember = member;
    input.value = `${member.rollNo} - ${member.firstName} ${member.lastName}`;
    dropdown.classList.remove('show');

    document.getElementById('selectedMemberRoll').textContent = member.rollNo;
    document.getElementById('selectedMemberName').textContent = `${member.firstName} ${member.lastName}`;
    document.getElementById('selectedMemberEmail').textContent = member.emailAddress;
    document.getElementById('selectedMemberDept').textContent = member.department;
    document.getElementById('selectedMemberPos').textContent = member.designation;

    infoBox.style.display = 'block';
  }
}

// -------------------------------
// File Input Handling
// -------------------------------
function initFileInputs() {
  const fileHandlers = [
    { inputId: 'artifactFile', previewId: 'filePreview', nameId: 'fileName' },
    { inputId: 'existingArtifactFile', previewId: 'existingFilePreview', nameId: 'existingFileName' }
  ];

  fileHandlers.forEach(({ inputId, previewId, nameId }) => {
    const input = document.getElementById(inputId);
    const preview = document.getElementById(previewId);
    const nameSpan = document.getElementById(nameId);

    input.addEventListener('change', function () {
      if (this.files && this.files[0]) {
        nameSpan.textContent = this.files[0].name;
        preview.classList.add('show');
      } else {
        preview.classList.remove('show');
      }
    });
  });
}

// -------------------------------
// Set Default Dates
// -------------------------------
function initDateDefaults() {
  document.getElementById('newCreatedDate').valueAsDate = new Date();
  document.getElementById('createdDate').valueAsDate = new Date();
}

// -------------------------------
// Form Submission
// -------------------------------
function initFormHandlers() {
  const existingForm = document.getElementById('existingMemberForm');

  existingForm.addEventListener('submit', function (e) {
    e.preventDefault();
    if (!selectedMember) {
      showNotification('Please select a member first!', 'error');
      return;
    }

    const formData = new FormData();
    const fileInput = document.getElementById('existingArtifactFile');
    const file = fileInput.files[0];
    if (!file) {
      showNotification('Please upload a file!', 'error');
      return;
    }

    formData.append('file', file);

    const dto = {
      rollNo: selectedMember.rollNo,
      firstName: selectedMember.firstName,
      lastName: selectedMember.lastName,
      emailAddress: selectedMember.emailAddress,
      phoneNumber: selectedMember.phoneNumber,
      department: selectedMember.department,
      designation: selectedMember.designation,
      artifactType: document.getElementById('artifactType').value,
      artifactTitle: document.getElementById('artifactTitle').value,
      createdDate: document.getElementById('createdDate').value,
      expiryDate: document.getElementById('expiryDate').value || null,
      issuer: document.getElementById('issuer').value,
      description: document.getElementById('description').value
    };

    formData.append('dto', new Blob([JSON.stringify(dto)], { type: 'application/json' }));

    fetch('/api/artifact/upload', {
      method: 'POST',
      body: formData
    })
    .then(res => {
      if (res.ok) {
        showNotification('Artifact stored for member successfully!', 'success');
        existingForm.reset();
        document.getElementById('existingFilePreview').classList.remove('show');
        document.getElementById('selectedMemberInfo').style.display = 'none';
        selectedMember = null;
        initDateDefaults();
      } else {
        throw new Error('Upload failed');
      }
    })
    .catch(() => showNotification('Something went wrong!', 'error'));
  });
}

// -------------------------------
// Notification Utility
// -------------------------------
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

// -------------------------------
// Fetch Departments from Backend
// -------------------------------
function fetchDepartments() {
  const deptSelect = document.getElementById('departmentSelect');
  if (!deptSelect) return;

  fetch('/api/member/find/departments')
    .then(res => {
      if (!res.ok) throw new Error('Failed to fetch departments');
      return res.json();
    })
    .then(departments => {
      deptSelect.innerHTML = '<option value="">Select Department</option>';
      departments.forEach(dept => {
        const option = document.createElement('option');
        option.value = dept;
        option.textContent = dept;
        deptSelect.appendChild(option);
      });
    })
    .catch(err => console.error('Error fetching departments:', err));
}

// -------------------------------
// Fetch Artifact Types from Backend
// -------------------------------
function fetchArtifactTypes() {
  const newSelect = document.getElementById('newArtifactType');
  const existingSelect = document.getElementById('artifactType');

  fetch('/api/artifact/find/all')
    .then(res => {
      if (!res.ok) throw new Error('Failed to fetch artifact types');
      return res.json();
    })
    .then(types => {
      const createOptions = (select) => {
        if (!select) return;
        select.innerHTML = '<option value="">Select Artifact Type</option>';
        types.forEach(({ name, displayName }) => {
          const option = document.createElement('option');
          option.value = name;
          option.textContent = displayName;
          select.appendChild(option);
        });
      };
      createOptions(newSelect);
      createOptions(existingSelect);
    })
    .catch(err => console.error('Error fetching artifact types:', err));
}

// Add smooth transitions and interactions
document.addEventListener('DOMContentLoaded', function() {
    const cards = document.querySelectorAll('.action-card');

    cards.forEach(card => {
        card.addEventListener('mouseenter', function() {
            this.style.transform = 'translateY(-8px) scale(1.02)';
        });

        card.addEventListener('mouseleave', function() {
            this.style.transform = 'translateY(0) scale(1)';
        });
    });
});
