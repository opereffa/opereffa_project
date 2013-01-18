release_cpp_object(pb_object:POINTER)
external
	"C++ inline use %"$header_file_name$%""
alias
	"[
	  $pb_object_type$* pbObj  = ($pb_object_type$*)\$pb_object;
	  delete pbObj;
	]"

end