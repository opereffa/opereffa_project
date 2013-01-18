add_person:PERSON_WRAPPER
require
	arr_list_not_null : person /= void
local
	new_person_ptr:POINTER
	new_person_wrapper:PERSON_WRAPPER
do
	new_person_ptr := add_person_to_pb_object(get_protoc_ptr)
	create new_person_wrapper
	new_person_wrapper.set_protoc_ptr (new_person_ptr)
	--add to eiffel collection for easy access later
	person.force(new_person_wrapper)
	Result := new_person_wrapper
end