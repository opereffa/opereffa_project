add_$field_name$:$field_type$
require
	$field_name$_not_null : $field_name$ /= void
local
	new_$field_name$_ptr:POINTER
	new_$field_name$_wrapper:$field_type$
do
	new_$field_name$_ptr := add_$field_name$_to_pb_object(get_protoc_ptr)
	create new_$field_name$_wrapper.make
	new_$field_name$_wrapper.set_protoc_ptr (new_$field_name$_ptr)
	--add to eiffel collection for easy access later
	$field_name$.force(new_$field_name$_wrapper)
	Result := new_$field_name$_wrapper
end