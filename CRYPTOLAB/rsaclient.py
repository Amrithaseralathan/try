import socket

def gcd(a, b):
    while b:
        a, b = b, a % b
    return a

def RSA_encrypt(message, e, n):
    return (message ** e) % n

if __name__ == "__main__":
    # Create a socket and connect to the server
    client_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_address = ('localhost', 12345)  # Use the same address and port as in server.py
    client_socket.connect(server_address)

    p = int(input("Enter p (a number for mod 26): "))
    q = int(input("Enter q (a number for mod 26): "))
    me = input("Enter message to encrypt (alphabets): ").upper()  # Convert input to uppercase

    # Convert alphabets to numbers using modulo 26
    me = [(ord(char) - ord('A')) % 26 for char in me]

    # Calculate n and totient t
    n = p * q
    t = (p - 1) * (q - 1)

    # Select public key, e
    for i in range(2, t):
        if gcd(i, t) == 1:
            e = i
            break

    encrypted_message = [RSA_encrypt(char, e, n) for char in me]

    # Concatenate n, e, encrypted message, p, and q into a single string separated by ','
    message_to_send = f"{n},{e},{':'.join(map(str, encrypted_message))},{p},{q}"

    print(f"Sending message: {message_to_send}")
    client_socket.send(message_to_send.encode())

    # Close the socket
    client_socket.close()
