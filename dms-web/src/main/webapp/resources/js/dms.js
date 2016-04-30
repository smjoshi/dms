/**
 * React.js code for dms page
 */

var productConf = [
           		{
           			"productId" : 1,
           			"docConfId" : 8,
           			"docTypeCode" : "FRONTIMAGE",
           			"isMandatory" : false,
           			"isMultipleItemAllowed" : false,
           			"groupId" : null,
           			"description" : null		
           		},
           		{
           			"productId" : 2,
           			"docConfId" : 9,
           			"docTypeCode" : "LEFTIMAGE",
           			"isMandatory" : false,
           			"isMultipleItemAllowed" : false,
           			"groupId" : null,
           			"description" : null		
           		},
           		{
           			"productId" : 3,
           			"docConfId" : 10,
           			"docTypeCode" : "RIGHTIMAGE",
           			"isMandatory" : false,
           			"isMultipleItemAllowed" : false,
           			"groupId" : null,
           			"description" : null		
           		},
           		{
           			"productId" : 4,
           			"docConfId" : 11,
           			"docTypeCode" : "BACKIMAGE",
           			"isMandatory" : false,
           			"isMultipleItemAllowed" : false,
           			"groupId" : null,
           			"description" : null		
           		}

           ]

var MyDocForm = React.createClass({
	
	getInitialState: function() {
	    return {
	    	awskey: "test_"+this.props.confData.docTypeCode,
	    	awsacl: "public-read-write",
	    	documentToUpload: ''
	    	};
	  },
	  
	docFileChange: function(e){
		console.log(e);
		this.setState({
			documentToUpload : e.target.value
		})
		
		
		var reader = new FileReader();
		var imgFile = this.refs.file.files[0];
		console.log(" Image to be uploaded : " + imgFile.name);
		var imgCmponent =  this.refs.thmbImg ;
		reader.onload = function(event) {
			imgCmponent.src = event.target.result;
			//console.log(event.target.result);
		}
		
		reader.readAsDataURL(imgFile);
		
		
	} , 
	handleForm: function(e){
		// make ajax call
		 e.preventDefault();
		
		console.log(e);
		console.log("Form State ==>" + this.state);
		
		var awsData = {
				key: "dev/"+this.refs.file.files[0].name,
				acl: this.state.awsacl,
		}
		
		console.log ("file to be uploaded : " + this.refs.file.files[0] );
		console.log ("AWS Key " + awsData.key );
		console.log ("AWS acl " + awsData.acl );
		var fd = new FormData();

		// fd.append( 'file', this.refs.file.files[0] );
		fd.append( 'acl', awsData.acl);
		fd.append( 'key', awsData.key);
		
		var files = this.refs.file.files;
		var file = files[0];
		
		console.log("File to Uploaded : " + file.name);
		console.log("File size : " + file.size);

		fd.append('file', file, file.name);

		console.log(fd.get(file.name));

		// Ajax call
		
		var urlToPost = 'http://devdmsproducts01.s3.amazonaws.com/';
		$.ajax({
			url: urlToPost,
			type: 'POST',
			data: fd,
			processData: false,
            contentType: false,
            // file: file,
			success: function(data){
				console.log("Successful upload");
			}.bind(this),
			error: function(xhr, status, err){
				console.log("Error while uploading the file");
				console.log(urlToPost, status, err);
			}.bind(this)
		});
	},	
	render: function(){
		return (
				<form ref="uploadForm" method="POST" encType="multipart/form-data" onSubmit={this.handleForm} >
				<div id="imgContainer" className="col-xs-6 col-md-3">
					<a href="#" id="imgThumbnail" className="thumbnail">
						<img ref="thmbImg" src="http://www.kerry-beaches.com/images/skellig-michael-puffin-small.jpg" />
					</a>
					<p>
						<input  type="hidden" name="key" value={this.state.awskey} />
						<input type="hidden" name="acl" value={this.state.awsacl} />
						<input ref="file" type="file" name="file" onChange={this.docFileChange} value={this.state.documentToUpload} id="imagefile" /> 
						<input type="submit" name="submit" value="Upload to Amazon S3" />		
						<button type="button" className="btn btn-danger">Remove</button>
					</p>       
				</div>
			</form>
		);
		
	}
});


var DocContainer = React.createClass({
	
	componentDidMount: function() {
		
		var urlToPost = "http://localhost:8080/api/details/product/"+this.props.productId;
	    return {
	    	    
	    	$.ajax({
	    	      url: urlToPost,
	    	      dataType: 'json',
	    	      cache: false,
	    	      success: function(data) {
	    	        this.setState({data: data});
	    	      }.bind(this),
	    	      error: function(xhr, status, err) {
	    	        console.error(this.props.url, status, err.toString());
	    	      }.bind(this)
	    	    });
	  },
	
	render: function(){
	// rendering DOM updates
		var formElements = [];
		console.log(this.props.confData);
		var confData = this.state.data;
	
		confData.forEach(function(conf){
			formElements.push(<MyDocForm confData={conf} key={conf.docConfId} />);
		});
		
		return(
					<div id="imageRow" className="row">
						{formElements}
					</div>
			  );
		}
	
});

ReactDOM.render(
	var product =  this.refs.productId ;
	console.log("Product under consideration : " + product);
	<DocContainer productId={product}/>,
	document.getElementById('dmscontainer')
);
	


