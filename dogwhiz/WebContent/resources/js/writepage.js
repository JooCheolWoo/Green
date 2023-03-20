let editor;
const form = document.getElementById("post-form");

CKEDITOR.ClassicEditor.create(document.getElementById("editor"), {
toolbar: {
	    items: [	
	        'bold', 'italic', 'strikethrough', 'underline', '|', 'alignment', '|',
	        'bulletedList', 'numberedList', '|', 'horizontalLine',
	        '-',
	        'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor', 'highlight', '|',
	        'link', 'insertImage', 'blockQuote', 'insertTable', 'mediaEmbed', '|',
	    ],
	    shouldNotGroupWhenFull: true
	},
	
	language: 'ko',
	
	ckfinder: { uploadUrl: '/dogwhiz/image/upload' },
	
	mediaEmbed: {
	    previewsInData: true
	},
	
	list: {
	    properties: {
	        styles: true,
	        startIndex: true,
	        reversed: true
	    }
	},
	heading: {
	    options: [
	        { model: 'paragraph', title: 'Paragraph', class: 'ck-heading_paragraph' },
	        { model: 'heading1', view: 'h1', title: 'Heading 1', class: 'ck-heading_heading1' },
	        { model: 'heading2', view: 'h2', title: 'Heading 2', class: 'ck-heading_heading2' },
	        { model: 'heading3', view: 'h3', title: 'Heading 3', class: 'ck-heading_heading3' },
	        { model: 'heading4', view: 'h4', title: 'Heading 4', class: 'ck-heading_heading4' },
	        { model: 'heading5', view: 'h5', title: 'Heading 5', class: 'ck-heading_heading5' },
	        { model: 'heading6', view: 'h6', title: 'Heading 6', class: 'ck-heading_heading6' }
	    ]
	},
	fontFamily: {
	    options: [
	        'default',
	        'Arial, Helvetica, sans-serif',
	        'Courier New, Courier, monospace',
	        'Georgia, serif',
	        'Lucida Sans Unicode, Lucida Grande, sans-serif',
	        'Tahoma, Geneva, sans-serif',
	        'Times New Roman, Times, serif',
	        'Trebuchet MS, Helvetica, sans-serif',
	        'Verdana, Geneva, sans-serif'
	    ],
	    supportAllValues: true
	},
	fontSize: {
	    options: [ 10, 12, 14, 'default', 18, 20, 22 ],
	    supportAllValues: true
	},
	htmlSupport: {
	    allow: [
	        {
	            name: /.*/,
	            attributes: true,
	            classes: true,
	            styles: true
	        }
	    ]
	},
	htmlEmbed: {
	    showPreviews: true
	},
	link: {
	    decorators: {
	        addTargetToExternalLinks: true,
	        defaultProtocol: 'https://',
	        toggleDownloadable: {
	            mode: 'manual',
	            label: 'Downloadable',
	            attributes: {
	                download: 'file'
	            }
	        }
	    }
	},
	
	// The "super-build" contains more premium features that require additional configuration, disable them below.
	// Do not turn them on unless you read the documentation and know how to configure them and setup the editor.
	removePlugins: [
	    // These two are commercial, but you can try them out without registering to a trial.
	    // 'ExportPdf',
	    // 'ExportWord',
	    'CKBox',
	    'EasyImage',
	    // This sample uses the Base64UploadAdapter to handle image uploads as it requires no configuration.
	    // https://ckeditor.com/docs/ckeditor5/latest/features/images/image-upload/base64-upload-adapter.html
	    // Storing images as Base64 is usually a very bad idea.
	    // Replace it on production website with other solutions:
	    // https://ckeditor.com/docs/ckeditor5/latest/features/images/image-upload/image-upload.html
	    // 'Base64UploadAdapter',
	    'RealTimeCollaborativeComments',
	    'RealTimeCollaborativeTrackChanges',
	    'RealTimeCollaborativeRevisionHistory',
	    'PresenceList',
	    'Comments',
	    'TrackChanges',
	    'TrackChangesData',
	    'RevisionHistory',
	    'Pagination',
	    'WProofreader',
	    // Careful, with the Mathtype plugin CKEditor will not load when loading this sample
	    // from a local file system (file://) - load this site via HTTP server if you enable MathType
	    'MathType'
	]
	}).then(editor => {
		window.editor = editor;
		console.log('Editor was initialized');
	}).catch(error => {
		console.error(error);
});
	

function submitForm() {
	let category = document.getElementById("category").value;
	let form = document.getElementById("post-form");
	let url;
	switch (category) {
	    case "공지":
	        url = "./announcement/add";
	        form.setAttribute("modelAttribute", "announcement");
	        break;
	    case "커뮤니티":
	        url = "./community/add";
	        form.setAttribute("modelAttribute", "community");
	        break;
	    case "개과사전":
	        url = "./dictionary/add";
	        form.setAttribute("modelAttribute", "dictionary");
	        break;
	    case "이벤트":
	        url = "./event/add";
	        form.setAttribute("modelAttribute", "event");
	        break;
	    case "피드백":
	        url = "./feedback/add";
	        form.setAttribute("modelAttribute", "feedback");
	        break;
	    default:
	        break;
	}

	form.setAttribute("action", url);

  // 에디터의 내용을 textarea 요소에 저장
  let editorData = window.editor.getData();
  document.getElementById("content").value = editorData;
  console.log(document.getElementById("content").value);
  
    // base64 인코딩된 이미지 데이터 가져오기
    let base64Image = document.getElementById("base64Image");
    console.log(base64Image);
    // 이미지 데이터가 있으면, content의 value에 추가
    if (base64Image) {
        document.getElementById("content").value += "<img src='" + base64Image + "'>";
    }	  
  
  document.getElementById("post-form").submit();
}


const categorySelect = document.getElementById('category');
const importantDiv = document.querySelector('.important');
const subCategoryCommunityDiv = document.querySelector('.subCategoryCommunity');
const subCategoryDictionaryDiv = document.querySelector('.subCategoryDictionary');

function chageList() {
    if (categorySelect.value === '공지') {
        importantDiv.style.display = 'block';
        subCategoryCommunityDiv.style.display = 'none';
        subCategoryDictionaryDiv.style.disply = 'none';
    } else if (categorySelect.value == '커뮤니티') {
        subCategoryCommunityDiv.style.display = 'block';
        importantDiv.style.display = 'none';
        subCategoryDictionaryDiv.style.disply = 'none';
    } else if (categorySelect.value == '개과사전') {
    	subCategoryDictionaryDiv.style.display = 'block';
        subCategoryCommunityDiv.style.display = 'none';
        importantDiv.style.display = 'none';  
        console.log('제발');  	
    } else {
        subCategoryCommunityDiv.style.display = 'none';
        importantDiv.style.display = 'none';
        subCategoryDictionaryDiv.style.disply = 'none';
    }    
}

// 페이지 로드 및 카테고리 변경시 함수 호출
chageList();
categorySelect.addEventListener('change', chageList);

