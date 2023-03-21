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


const categorySelect = document.getElementById("category");
const subCategoryCommunityDiv = document.querySelector(".subCategoryCommunity");
const subCategoryDictionaryDiv = document.querySelector(".subCategoryDictionary");
const importantDiv = document.querySelector(".important");
const subCategoryCommunitySelect = document.getElementById("subCategoryCommunity");
const subCategoryDictionarySelect = document.getElementById("subCategoryDictionary");

function handleCategoryChange() {
  if (categorySelect.value === "공지사항") {
    importantDiv.style.display = "block";
    subCategoryCommunityDiv.style.display = "none";
    subCategoryDictionaryDiv.style.display = "none";
    subCategoryCommunitySelect.removeAttribute("subCategory");
    subCategoryDictionarySelect.removeAttribute("subCategory");
  } else {
    importantDiv.style.display = "none";
    if (categorySelect.value === "커뮤니티") {
      subCategoryCommunityDiv.style.display = "block";
      subCategoryDictionaryDiv.style.display = "none";
      subCategoryCommunitySelect.setAttribute("name", "subCategory");
      subCategoryDictionarySelect.removeAttribute("name");
    } else if (categorySelect.value === "개과사전") {
      subCategoryCommunityDiv.style.display = "none";
      subCategoryDictionaryDiv.style.display = "block";
      subCategoryCommunitySelect.removeAttribute("name");
      subCategoryDictionarySelect.setAttribute("name", "subCategory");
    } else {
      subCategoryCommunityDiv.style.display = "none";
      subCategoryDictionaryDiv.style.display = "none";
      subCategoryCommunitySelect.removeAttribute("name");
      subCategoryDictionarySelect.removeAttribute("name");
    }
  }
}



window.addEventListener("load", function() {
  handleCategoryChange();

  categorySelect.addEventListener("change", handleCategoryChange);

});



